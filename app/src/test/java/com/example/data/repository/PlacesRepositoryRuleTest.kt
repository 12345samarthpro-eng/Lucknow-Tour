package com.example.data.repository

import com.example.base.FirestoreEmulatorTestBase
import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory
import com.example.data.seed.LucknowCatalogSeed
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.util.UUID

class PlacesRepositoryRuleTest : FirestoreEmulatorTestBase() {

    @Test
    fun catalogSeed_hasMoreThan100UniqueLucknowPlaces() {
        val all = LucknowCatalogSeed.allPlaces
        assertTrue("Expected > 100 places, found ${all.size}", all.size > 100)
        assertEquals("All place IDs must be unique", all.size, all.map { it.id }.toSet().size)
    }

    @Test
    fun saveCustomPlace_authenticatedUser_createsAndObservesInRealtime() = runBlocking {
        val uid = signInTestUser(ALICE_EMAIL)
        val repository = PlacesRepository(firestore)
        val slugId = "custom_place_${UUID.randomUUID().toString().take(8)}"

        val place = LucknowPlace(
            id = slugId,
            name = "Hazratganj Heritage Coffee",
            category = PlaceCategory.FOOD.label,
            locality = "Hazratganj",
            address = "MG Marg, Hazratganj, Lucknow",
            timings = "9:00 AM - 10:00 PM",
            ticketPrice = "Free Entry",
            description = "Classic Lucknowi espresso and bun maska spot.",
            highlights = listOf("Heritage Seating", "Bun Maska"),
            photoUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/a/a6/Hazratganj_Lucknow.jpg"),
            reviewVideoUrl = "https://www.youtube.com/results?search_query=Hazratganj+Lucknow",
            reviewVideoTitle = "Hazratganj Evening Walk",
            reviewSummary = "Cozy spot for evening coffee.",
            rating = 4.6,
            reviewCount = 420L,
            latitude = 26.8498,
            longitude = 80.9452
        )

        val createResult = withTimeout(DEFAULT_TIMEOUT_MS) {
            repository.saveCustomPlace(uid, place, ALICE_EMAIL)
        }
        assertTrue("Expected custom place save to succeed: ${createResult.exceptionOrNull()}", createResult.isSuccess)

        val observed = withTimeout(FLOW_TIMEOUT_MS) {
            repository.observeCustomPlaces(uid).first { list -> list.any { it.id == slugId } }
        }
        assertTrue(observed.any { it.id == slugId && it.name == "Hazratganj Heritage Coffee" })
    }

    @Test
    fun updateSavedPlaceState_authenticatedUser_persistsBookmarkAndNote() = runBlocking {
        val uid = signInTestUser(ALICE_EMAIL)
        val repository = PlacesRepository(firestore)
        val placeId = "bara_imambara_${UUID.randomUUID().toString().take(6)}"

        val result = withTimeout(DEFAULT_TIMEOUT_MS) {
            repository.updateSavedPlaceState(
                userId = uid,
                placeId = placeId,
                isBookmarked = true,
                isFavorite = true,
                isVisited = true,
                personalNote = "Reach by 7 AM for golden light.",
                userRating = 5.0
            )
        }
        assertTrue("Expected saved place update to succeed: ${result.exceptionOrNull()}", result.isSuccess)

        val savedMap = withTimeout(FLOW_TIMEOUT_MS) {
            repository.observeSavedPlaces(uid).first { map -> map.containsKey(placeId) }
        }
        val saved = savedMap[placeId]
        assertNotNull(saved)
        assertEquals(true, saved?.isBookmarked)
        assertEquals(true, saved?.isFavorite)
        assertEquals(true, saved?.isVisited)
        assertEquals("Reach by 7 AM for golden light.", saved?.personalNote)

        val unfavoriteResult = withTimeout(DEFAULT_TIMEOUT_MS) {
            repository.updateSavedPlaceState(
                userId = uid,
                placeId = placeId,
                isFavorite = false
            )
        }
        assertTrue("Expected unfavorite update to succeed: ${unfavoriteResult.exceptionOrNull()}", unfavoriteResult.isSuccess)
        val updatedMap = withTimeout(FLOW_TIMEOUT_MS) {
            repository.observeSavedPlaces(uid).first { map -> map[placeId]?.isFavorite == false }
        }
        assertEquals(false, updatedMap[placeId]?.isFavorite)
        assertEquals(true, updatedMap[placeId]?.isBookmarked)
    }

    @Test
    fun saveGlobalPlace_nonAdminVisitor_failsWithPermissionDenied() = runBlocking {
        signInTestUser(BOB_EMAIL)
        val repository = PlacesRepository(firestore)
        val place = LucknowCatalogSeed.allPlaces.first().copy(id = "non_admin_test_${UUID.randomUUID().toString().take(6)}")

        val result = withTimeout(DEFAULT_TIMEOUT_MS) {
            repository.saveGlobalPlace(place, BOB_EMAIL)
        }
        assertTrue("Expected non-admin global place write to fail", result.isFailure)
        val firestoreException = result.exceptionOrNull()?.unwrapFirestoreException()
        assertNotNull("Expected FirebaseFirestoreException", firestoreException)
        assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreException?.code)
    }

    @Test
    fun observePublishedPlaces_unauthenticatedUser_failsWithPermissionDenied() = runBlocking {
        auth.signOut()
        val repository = PlacesRepository(firestore)

        try {
            withTimeout(FLOW_TIMEOUT_MS) { repository.observePublishedPlaces().first() }
            fail("Expected PERMISSION_DENIED for unauthenticated caller")
        } catch (e: Exception) {
            val firestoreException = e.unwrapFirestoreException()
                ?: if (e is IllegalStateException) null else throw e
            if (firestoreException != null) {
                assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreException.code)
            }
        }
    }

    private companion object {
        const val ALICE_EMAIL = "alice_lucknow@test.com"
        const val BOB_EMAIL = "bob_lucknow@test.com"
        const val DEFAULT_TIMEOUT_MS = 5000L
        const val FLOW_TIMEOUT_MS = 3000L
    }
}
