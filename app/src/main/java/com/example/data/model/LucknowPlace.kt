package com.example.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp

enum class PlaceCategory(
    val label: String,
    val fallbackPhotoUrl: String
) {
    HERITAGE(
        "Heritage & Monuments",
        "https://commons.wikimedia.org/wiki/Special:FilePath/Bara_Imambara_Lucknow.jpg?width=900"
    ),
    PARKS(
        "Parks & Nature",
        "https://commons.wikimedia.org/wiki/Special:FilePath/Ambedkar_Park,_Lucknow,_India.jpg?width=900"
    ),
    SPIRITUAL(
        "Temples & Spiritual",
        "https://commons.wikimedia.org/wiki/Special:FilePath/MANKAMESHVER_TEMPLE.jpg?width=900"
    ),
    MUSEUMS(
        "Museums & Culture",
        "https://commons.wikimedia.org/wiki/Special:FilePath/Lucknow_Museum.jpg?width=900"
    ),
    FOOD(
        "Food & Cuisine",
        "https://commons.wikimedia.org/wiki/Special:FilePath/Tunday_Kababi,_Lucknow.jpg?width=900"
    ),
    MARKETS(
        "Markets & Shopping",
        "https://commons.wikimedia.org/wiki/Special:FilePath/Hazratganj_Lucknow.jpg?width=900"
    ),
    DAY_TRIPS(
        "Day Trips & Leisure",
        "https://commons.wikimedia.org/wiki/Special:FilePath/Nawabganj_Bird_Sanctuary,_Unnao_02.jpg?width=900"
    );

    companion object {
        fun fromLabel(label: String): PlaceCategory {
            return entries.firstOrNull { it.label.equals(label, ignoreCase = true) } ?: HERITAGE
        }

        val allLabels: List<String> = entries.map { it.label }
    }
}

@IgnoreExtraProperties
data class LucknowPlace(
    @DocumentId val id: String = "",
    val name: String = "",
    val category: String = PlaceCategory.HERITAGE.label,
    val locality: String = "",
    val address: String = "",
    val timings: String = "",
    val closedOn: String = "Open all days",
    val ticketPrice: String = "Free Entry",
    val bestTimeToVisit: String = "Morning & Evening",
    val description: String = "",
    val highlights: List<String> = emptyList(),
    val photoUrls: List<String> = emptyList(),
    val reviewVideoUrl: String = "",
    val reviewVideoTitle: String = "",
    val reviewSummary: String = "",
    val rating: Double = 4.5,
    val reviewCount: Long = 100L,
    val latitude: Double = 26.8467,
    val longitude: Double = 80.9462,
    @get:PropertyName("isPublished") @field:PropertyName("isPublished") val isPublished: Boolean = true,
    @get:PropertyName("isFeatured") @field:PropertyName("isFeatured") val isFeatured: Boolean = false,
    val ownerId: String = "",
    val updatedByEmail: String = "",
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null,
    // Local-only flag to distinguish cloud-synced vs bundled seed vs user custom override
    val sourceLabel: String = "Curated Guide"
) {
    fun fallbackPhotoUrl(): String = PlaceCategory.fromLabel(category).fallbackPhotoUrl

    fun isFreeEntry(): Boolean {
        val lower = ticketPrice.lowercase()
        return lower.contains("free") || lower.contains("no entry")
    }

    fun toFirestoreMap(
        ownerUid: String,
        editorEmail: String,
        preserveCreatedAt: Timestamp? = null
    ): Map<String, Any> {
        return mapOf(
            "name" to name.trim().take(120),
            "category" to category.trim().take(50).ifEmpty { PlaceCategory.HERITAGE.label },
            "locality" to locality.trim().take(120).ifEmpty { "Lucknow" },
            "address" to address.trim().take(250).ifEmpty { "Lucknow, Uttar Pradesh" },
            "timings" to timings.trim().take(150).ifEmpty { "9:00 AM - 6:00 PM" },
            "closedOn" to closedOn.trim().take(80),
            "ticketPrice" to ticketPrice.trim().take(150).ifEmpty { "Free Entry" },
            "bestTimeToVisit" to bestTimeToVisit.trim().take(120),
            "description" to description.trim().take(1500).ifEmpty { "Popular destination in Lucknow." },
            "highlights" to highlights.map { it.trim() }.filter { it.isNotEmpty() }.take(10),
            "photoUrls" to photoUrls.map { it.trim() }.filter { it.isNotEmpty() }.take(12),
            "reviewVideoUrl" to reviewVideoUrl.trim().take(500),
            "reviewVideoTitle" to reviewVideoTitle.trim().take(180),
            "reviewSummary" to reviewSummary.trim().take(800),
            "rating" to rating.coerceIn(0.0, 5.0),
            "reviewCount" to reviewCount.coerceAtLeast(0L),
            "latitude" to latitude.coerceIn(-90.0, 90.0),
            "longitude" to longitude.coerceIn(-180.0, 180.0),
            "isPublished" to isPublished,
            "isFeatured" to isFeatured,
            "ownerId" to ownerUid,
            "updatedByEmail" to editorEmail.trim().take(120),
            "createdAt" to (preserveCreatedAt ?: FieldValue.serverTimestamp()),
            "updatedAt" to FieldValue.serverTimestamp()
        )
    }

    companion object {
        fun fromSnapshot(doc: DocumentSnapshot, sourceLabel: String): LucknowPlace? {
            if (!doc.exists()) return null
            val highlightsList = (doc.get("highlights") as? List<*>)
                ?.mapNotNull { it as? String }
                ?: emptyList()
            val photoList = (doc.get("photoUrls") as? List<*>)
                ?.mapNotNull { it as? String }
                ?: emptyList()
            return LucknowPlace(
                id = doc.id,
                name = doc.getString("name").orEmpty(),
                category = doc.getString("category").orEmpty().ifEmpty { PlaceCategory.HERITAGE.label },
                locality = doc.getString("locality").orEmpty(),
                address = doc.getString("address").orEmpty(),
                timings = doc.getString("timings").orEmpty(),
                closedOn = doc.getString("closedOn").orEmpty().ifEmpty { "Open all days" },
                ticketPrice = doc.getString("ticketPrice").orEmpty().ifEmpty { "Free Entry" },
                bestTimeToVisit = doc.getString("bestTimeToVisit").orEmpty().ifEmpty { "Morning & Evening" },
                description = doc.getString("description").orEmpty(),
                highlights = highlightsList,
                photoUrls = photoList,
                reviewVideoUrl = doc.getString("reviewVideoUrl").orEmpty(),
                reviewVideoTitle = doc.getString("reviewVideoTitle").orEmpty(),
                reviewSummary = doc.getString("reviewSummary").orEmpty(),
                rating = (doc.get("rating") as? Number)?.toDouble() ?: 4.5,
                reviewCount = (doc.get("reviewCount") as? Number)?.toLong() ?: 100L,
                latitude = (doc.get("latitude") as? Number)?.toDouble() ?: 26.8467,
                longitude = (doc.get("longitude") as? Number)?.toDouble() ?: 80.9462,
                isPublished = doc.getBoolean("isPublished") ?: true,
                isFeatured = doc.getBoolean("isFeatured") ?: false,
                ownerId = doc.getString("ownerId").orEmpty(),
                updatedByEmail = doc.getString("updatedByEmail").orEmpty(),
                createdAt = doc.getTimestamp("createdAt", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE),
                updatedAt = doc.getTimestamp("updatedAt", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE),
                sourceLabel = sourceLabel
            )
        }
    }
}

@IgnoreExtraProperties
data class SavedPlace(
    @DocumentId val id: String = "",
    val placeId: String = "",
    @get:PropertyName("isBookmarked") @field:PropertyName("isBookmarked") val isBookmarked: Boolean = false,
    @get:PropertyName("isFavorite") @field:PropertyName("isFavorite") val isFavorite: Boolean = false,
    @get:PropertyName("isVisited") @field:PropertyName("isVisited") val isVisited: Boolean = false,
    val personalNote: String = "",
    val userRating: Double = 0.0,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
) {
    fun toFirestoreMap(preserveCreatedAt: Timestamp? = null): Map<String, Any> {
        return mapOf(
            "placeId" to placeId,
            "isBookmarked" to isBookmarked,
            "isFavorite" to isFavorite,
            "isVisited" to isVisited,
            "personalNote" to personalNote.take(500),
            "userRating" to userRating.coerceIn(0.0, 5.0),
            "createdAt" to (preserveCreatedAt ?: FieldValue.serverTimestamp()),
            "updatedAt" to FieldValue.serverTimestamp()
        )
    }

    companion object {
        fun fromSnapshot(doc: DocumentSnapshot): SavedPlace? {
            if (!doc.exists()) return null
            return SavedPlace(
                id = doc.id,
                placeId = doc.getString("placeId").orEmpty().ifEmpty { doc.id },
                isBookmarked = doc.getBoolean("isBookmarked") ?: false,
                isFavorite = doc.getBoolean("isFavorite") ?: false,
                isVisited = doc.getBoolean("isVisited") ?: false,
                personalNote = doc.getString("personalNote").orEmpty(),
                userRating = (doc.get("userRating") as? Number)?.toDouble() ?: 0.0,
                createdAt = doc.getTimestamp("createdAt", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE),
                updatedAt = doc.getTimestamp("updatedAt", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
            )
        }
    }
}

@IgnoreExtraProperties
data class UserProfile(
    @DocumentId val id: String = "",
    val userId: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)
