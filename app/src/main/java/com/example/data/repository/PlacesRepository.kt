package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.data.model.LucknowPlace
import com.example.data.model.SavedPlace
import com.example.data.model.UserProfile
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID

class PlacesRepository(private val db: FirebaseFirestore) {

    constructor(context: Context) : this(
        FirebaseFirestore.getInstance(
            context.applicationContext.getString(R.string.firestore_database_id)
        )
    )

    private fun requireUserId(): String {
        return Firebase.auth.currentUser?.uid
            ?: throw IllegalStateException("User must be signed in with Google before accessing Firestore.")
    }

    fun observePublishedPlaces(): Flow<List<LucknowPlace>> = flow {
        val uid = requireUserId()
        val path = "places"
        emitAll(
            db.collection(path)
                .whereEqualTo("isPublished", true)
                .snapshots()
                .map { snapshot ->
                    snapshot.documents.mapNotNull { doc ->
                        LucknowPlace.fromSnapshot(doc, sourceLabel = "Live Cloud")
                    }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && Firebase.auth.currentUser?.uid == uid) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    fun observeSavedPlaces(userId: String): Flow<Map<String, SavedPlace>> = flow {
        val uid = requireUserId()
        require(uid == userId) { "User ID mismatch" }
        val path = "users/$userId/saved_places"
        emitAll(
            db.collection("users").document(userId).collection("saved_places")
                .snapshots()
                .map { snapshot ->
                    snapshot.documents.mapNotNull { doc ->
                        SavedPlace.fromSnapshot(doc)
                    }.associateBy { it.id.ifEmpty { it.placeId } }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && Firebase.auth.currentUser?.uid == uid) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    fun observeCustomPlaces(userId: String): Flow<List<LucknowPlace>> = flow {
        val uid = requireUserId()
        require(uid == userId) { "User ID mismatch" }
        val path = "users/$userId/custom_places"
        emitAll(
            db.collection("users").document(userId).collection("custom_places")
                .snapshots()
                .map { snapshot ->
                    snapshot.documents.mapNotNull { doc ->
                        LucknowPlace.fromSnapshot(doc, sourceLabel = "My Update")
                    }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && Firebase.auth.currentUser?.uid == uid) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    suspend fun upsertUserProfile(
        userId: String,
        displayName: String,
        email: String,
        photoUrl: String
    ): Result<Unit> {
        val docRef = db.collection("users").document(userId)
        return try {
            val uid = requireUserId()
            require(uid == userId) { "Cannot write profile for another user" }
            val existing = docRef.get().await()
            val safeName = displayName.trim().ifEmpty { "Lucknow Explorer" }.take(80)
            if (existing.exists()) {
                val createdAt = existing.getTimestamp("createdAt") ?: Timestamp.now()
                docRef.set(
                    mapOf(
                        "userId" to uid,
                        "displayName" to safeName,
                        "email" to email.take(120),
                        "photoUrl" to photoUrl.take(500),
                        "createdAt" to createdAt,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            } else {
                docRef.set(
                    mapOf(
                        "userId" to uid,
                        "displayName" to safeName,
                        "email" to email.take(120),
                        "photoUrl" to photoUrl.take(500),
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docRef.path)
            Result.failure(e)
        }
    }

    suspend fun saveGlobalPlace(
        place: LucknowPlace,
        editorEmail: String
    ): Result<String> {
        val uid = try {
            requireUserId()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val docId = sanitizeDocId(place.id.ifBlank { generateSlugId(place.name) })
        val docRef = db.collection("places").document(docId)
        return try {
            val existingSnap = docRef.get().await()
            if (existingSnap.exists()) {
                val existingCreatedAt = existingSnap.getTimestamp("createdAt")
                val existingOwnerId = existingSnap.getString("ownerId").orEmpty().ifBlank { uid }
                val payload = place.toFirestoreMap(
                    ownerUid = existingOwnerId,
                    editorEmail = editorEmail,
                    preserveCreatedAt = existingCreatedAt
                )
                docRef.set(payload).await()
            } else {
                val payload = place.toFirestoreMap(
                    ownerUid = uid,
                    editorEmail = editorEmail,
                    preserveCreatedAt = null
                )
                docRef.set(payload).await()
            }
            Result.success(docId)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docRef.path)
            Result.failure(e)
        }
    }

    suspend fun deleteGlobalPlace(placeId: String): Result<Unit> {
        val docId = sanitizeDocId(placeId)
        val docRef = db.collection("places").document(docId)
        return try {
            requireUserId()
            docRef.delete().await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, docRef.path)
            Result.failure(e)
        }
    }

    suspend fun saveCustomPlace(
        userId: String,
        place: LucknowPlace,
        editorEmail: String
    ): Result<String> {
        val uid = try {
            requireUserId()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        require(uid == userId) { "User ID mismatch" }
        val docId = sanitizeDocId(place.id.ifBlank { generateSlugId(place.name) })
        val docRef = db.collection("users").document(userId).collection("custom_places").document(docId)
        return try {
            val existingSnap = docRef.get().await()
            if (existingSnap.exists()) {
                val existingCreatedAt = existingSnap.getTimestamp("createdAt")
                val payload = place.toFirestoreMap(
                    ownerUid = uid,
                    editorEmail = editorEmail,
                    preserveCreatedAt = existingCreatedAt
                )
                docRef.set(payload).await()
            } else {
                val payload = place.toFirestoreMap(
                    ownerUid = uid,
                    editorEmail = editorEmail,
                    preserveCreatedAt = null
                )
                docRef.set(payload).await()
            }
            Result.success(docId)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docRef.path)
            Result.failure(e)
        }
    }

    suspend fun deleteCustomPlace(userId: String, placeId: String): Result<Unit> {
        val docId = sanitizeDocId(placeId)
        val docRef = db.collection("users").document(userId).collection("custom_places").document(docId)
        return try {
            val uid = requireUserId()
            require(uid == userId) { "User ID mismatch" }
            docRef.delete().await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, docRef.path)
            Result.failure(e)
        }
    }

    suspend fun seedCatalogToCloud(
        places: List<LucknowPlace>,
        editorEmail: String
    ): Result<Int> {
        val uid = try {
            requireUserId()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return try {
            var syncedCount = 0
            val chunks = places.chunked(250)
            for (chunk in chunks) {
                val batch = db.batch()
                for (place in chunk) {
                    val docId = sanitizeDocId(place.id.ifBlank { generateSlugId(place.name) })
                    val docRef = db.collection("places").document(docId)
                    val payload = place.toFirestoreMap(
                        ownerUid = uid,
                        editorEmail = editorEmail,
                        preserveCreatedAt = null
                    )
                    batch.set(docRef, payload)
                    syncedCount++
                }
                batch.commit().await()
            }
            Result.success(syncedCount)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, "places")
            Result.failure(e)
        }
    }

    suspend fun updateSavedPlaceState(
        userId: String,
        placeId: String,
        isBookmarked: Boolean? = null,
        isFavorite: Boolean? = null,
        isVisited: Boolean? = null,
        personalNote: String? = null,
        userRating: Double? = null
    ): Result<Unit> {
        val docId = sanitizeDocId(placeId)
        val docRef = db.collection("users").document(userId).collection("saved_places").document(docId)
        return try {
            val uid = requireUserId()
            require(uid == userId) { "User ID mismatch" }
            val existingSnap = docRef.get().await()
            val existing = if (existingSnap.exists()) {
                SavedPlace.fromSnapshot(existingSnap)
            } else {
                null
            }
            val merged = SavedPlace(
                id = docId,
                placeId = docId,
                isBookmarked = isBookmarked ?: existing?.isBookmarked ?: false,
                isFavorite = isFavorite ?: existing?.isFavorite ?: false,
                isVisited = isVisited ?: existing?.isVisited ?: false,
                personalNote = (personalNote ?: existing?.personalNote ?: "").take(500),
                userRating = (userRating ?: existing?.userRating ?: 0.0).coerceIn(0.0, 5.0),
                createdAt = existing?.createdAt
            )
            docRef.set(merged.toFirestoreMap(preserveCreatedAt = existing?.createdAt)).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docRef.path)
            Result.failure(e)
        }
    }

    companion object {
        fun sanitizeDocId(raw: String): String {
            val cleaned = raw.trim()
                .lowercase()
                .replace(Regex("[^a-z0-9_\\-]"), "_")
                .replace(Regex("_+"), "_")
                .trim('_')
                .take(120)
            return cleaned.ifEmpty { "place_${UUID.randomUUID().toString().replace("-", "").take(12)}" }
        }

        fun generateSlugId(name: String): String {
            val base = sanitizeDocId(name).take(60)
            val suffix = UUID.randomUUID().toString().take(6)
            return "${base}_$suffix"
        }
    }
}
