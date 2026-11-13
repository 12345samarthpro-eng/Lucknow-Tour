package com.example.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory
import com.example.data.model.SavedPlace
import com.example.data.repository.PlacesRepository
import com.example.data.seed.LucknowCatalogSeed
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class QuickFilter(val label: String) {
    ALL("All"),
    FEATURED("Must-Visit"),
    FREE_ENTRY("Free Entry"),
    FAVORITES("Favorites"),
    BOOKMARKED("Saved"),
    VISITED("Visited")
}

enum class SortOption(val label: String) {
    FEATURED("Curated"),
    TOP_RATED("Top Rated"),
    ALPHABETICAL("A – Z")
}

data class LucknowUiState(
    val isLoading: Boolean = true,
    val allPlaces: List<LucknowPlace> = emptyList(),
    val filteredPlaces: List<LucknowPlace> = emptyList(),
    val savedMap: Map<String, SavedPlace> = emptyMap(),
    val cloudPlacesCount: Int = 0,
    val customPlacesCount: Int = 0,
    val searchQuery: String = "",
    val selectedCategory: String? = null,
    val quickFilter: QuickFilter = QuickFilter.ALL,
    val sortOption: SortOption = SortOption.FEATURED,
    val isSaving: Boolean = false,
    val isSyncingCloud: Boolean = false,
    val statusMessage: String? = null,
    val errorMessage: String? = null
) {
    val visitedCount: Int get() = savedMap.values.count { it.isVisited }
    val bookmarkedCount: Int get() = savedMap.values.count { it.isBookmarked }
    val favoritesCount: Int get() = savedMap.values.count { it.isFavorite }
}

class LucknowViewModel(
    private val repository: PlacesRepository,
    private val currentUserId: String,
    private val userEmail: String,
    private val displayName: String,
    private val userPhotoUrl: String,
    val isOwnerAdmin: Boolean
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow<String?>(null)
    private val _quickFilter = MutableStateFlow(QuickFilter.ALL)
    private val _sortOption = MutableStateFlow(SortOption.FEATURED)
    private val _isSaving = MutableStateFlow(false)
    private val _isSyncingCloud = MutableStateFlow(false)
    private val _statusMessage = MutableStateFlow<String?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)

    private data class FilterInputs(
        val query: String,
        val category: String?,
        val quickFilter: QuickFilter,
        val sortOption: SortOption
    )

    private data class MutationInputs(
        val isSaving: Boolean,
        val isSyncingCloud: Boolean,
        val statusMessage: String?,
        val errorMessage: String?
    )

    init {
        viewModelScope.launch {
            repository.upsertUserProfile(
                userId = currentUserId,
                displayName = displayName,
                email = userEmail,
                photoUrl = userPhotoUrl
            )
        }
    }

    private val cloudPlacesFlow = repository.observePublishedPlaces()
        .catch { error ->
            val firestoreError = generateSequence(error) { it.cause }
                .filterIsInstance<FirebaseFirestoreException>()
                .firstOrNull()
            if (firestoreError == null && error is CancellationException) throw error
            val rootError = firestoreError ?: error
            Log.w(TAG, "Error observing global places", rootError)
            _errorMessage.value = rootError.message ?: "Unable to sync live cloud updates."
            emit(emptyList())
        }

    private val savedPlacesFlow = repository.observeSavedPlaces(currentUserId)
        .catch { error ->
            val firestoreError = generateSequence(error) { it.cause }
                .filterIsInstance<FirebaseFirestoreException>()
                .firstOrNull()
            if (firestoreError == null && error is CancellationException) throw error
            val rootError = firestoreError ?: error
            Log.w(TAG, "Error observing saved places", rootError)
            emit(emptyMap())
        }

    private val customPlacesFlow = repository.observeCustomPlaces(currentUserId)
        .catch { error ->
            val firestoreError = generateSequence(error) { it.cause }
                .filterIsInstance<FirebaseFirestoreException>()
                .firstOrNull()
            if (firestoreError == null && error is CancellationException) throw error
            val rootError = firestoreError ?: error
            Log.w(TAG, "Error observing custom places", rootError)
            emit(emptyList())
        }

    private val filterInputsFlow = combine(
        _searchQuery,
        _selectedCategory,
        _quickFilter,
        _sortOption
    ) { query, category, quick, sort ->
        FilterInputs(query, category, quick, sort)
    }

    private val mutationInputsFlow = combine(
        _isSaving,
        _isSyncingCloud,
        _statusMessage,
        _errorMessage
    ) { saving, syncing, status, err ->
        MutationInputs(saving, syncing, status, err)
    }

    val uiState: StateFlow<LucknowUiState> = combine(
        cloudPlacesFlow,
        customPlacesFlow,
        savedPlacesFlow,
        filterInputsFlow,
        mutationInputsFlow
    ) { cloudPlaces, customPlaces, savedMap, filters, mutation ->
        // Merge hierarchy: Bundled 110 places -> overridden/extended by Live Cloud -> overridden/extended by User Custom Updates
        val mergedMap = LinkedHashMap<String, LucknowPlace>()
        LucknowCatalogSeed.allPlaces.forEach { mergedMap[it.id] = it }
        cloudPlaces.forEach { mergedMap[it.id] = it }
        customPlaces.forEach { mergedMap[it.id] = it }

        val allMerged = mergedMap.values.toList()
        val queryLower = filters.query.trim().lowercase()

        val filtered = allMerged.filter { place ->
            val matchesCategory = filters.category == null ||
                place.category.equals(filters.category, ignoreCase = true)

            val saved = savedMap[place.id]
            val matchesQuick = when (filters.quickFilter) {
                QuickFilter.ALL -> true
                QuickFilter.FEATURED -> place.isFeatured
                QuickFilter.FREE_ENTRY -> place.isFreeEntry()
                QuickFilter.FAVORITES -> saved?.isFavorite == true
                QuickFilter.BOOKMARKED -> saved?.isBookmarked == true
                QuickFilter.VISITED -> saved?.isVisited == true
            }

            val matchesQuery = if (queryLower.isEmpty()) {
                true
            } else {
                place.name.lowercase().contains(queryLower) ||
                    place.locality.lowercase().contains(queryLower) ||
                    place.category.lowercase().contains(queryLower) ||
                    place.description.lowercase().contains(queryLower) ||
                    place.highlights.any { it.lowercase().contains(queryLower) }
            }

            matchesCategory && matchesQuick && matchesQuery
        }.let { list ->
            when (filters.sortOption) {
                SortOption.FEATURED -> list.sortedWith(
                    compareByDescending<LucknowPlace> { it.isFeatured }
                        .thenByDescending { it.rating }
                        .thenBy { it.name }
                )
                SortOption.TOP_RATED -> list.sortedWith(
                    compareByDescending<LucknowPlace> { it.rating }
                        .thenByDescending { it.reviewCount }
                )
                SortOption.ALPHABETICAL -> list.sortedBy { it.name.lowercase() }
            }
        }

        LucknowUiState(
            isLoading = false,
            allPlaces = allMerged,
            filteredPlaces = filtered,
            savedMap = savedMap,
            cloudPlacesCount = cloudPlaces.size,
            customPlacesCount = customPlaces.size,
            searchQuery = filters.query,
            selectedCategory = filters.category,
            quickFilter = filters.quickFilter,
            sortOption = filters.sortOption,
            isSaving = mutation.isSaving,
            isSyncingCloud = mutation.isSyncingCloud,
            statusMessage = mutation.statusMessage,
            errorMessage = mutation.errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = LucknowUiState(
            isLoading = true,
            allPlaces = LucknowCatalogSeed.allPlaces,
            filteredPlaces = LucknowCatalogSeed.allPlaces
        )
    )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String?) {
        _selectedCategory.value = category
    }

    fun onQuickFilterSelected(filter: QuickFilter) {
        _quickFilter.value = filter
    }

    fun onSortSelected(sort: SortOption) {
        _sortOption.value = sort
    }

    fun clearStatusMessage() {
        _statusMessage.value = null
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun toggleBookmark(placeId: String) {
        val current = uiState.value.savedMap[placeId]?.isBookmarked ?: false
        viewModelScope.launch {
            val result = repository.updateSavedPlaceState(
                userId = currentUserId,
                placeId = placeId,
                isBookmarked = !current
            )
            if (result.isSuccess) {
                _statusMessage.value = if (!current) "Saved to My Lucknow Trip" else "Removed from Saved"
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Failed to update bookmark."
            }
        }
    }

    fun toggleFavorite(placeId: String) {
        val current = uiState.value.savedMap[placeId]?.isFavorite ?: false
        viewModelScope.launch {
            val result = repository.updateSavedPlaceState(
                userId = currentUserId,
                placeId = placeId,
                isFavorite = !current
            )
            if (result.isSuccess) {
                _statusMessage.value = if (!current) "Added to Favorites" else "Removed from Favorites"
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Failed to update favorite."
            }
        }
    }

    fun toggleVisited(placeId: String) {
        val current = uiState.value.savedMap[placeId]?.isVisited ?: false
        viewModelScope.launch {
            val result = repository.updateSavedPlaceState(
                userId = currentUserId,
                placeId = placeId,
                isVisited = !current
            )
            if (result.isSuccess) {
                _statusMessage.value = if (!current) "Checked in as Visited!" else "Removed Visited mark"
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Failed to update visited state."
            }
        }
    }

    fun savePersonalNote(placeId: String, note: String, rating: Double) {
        viewModelScope.launch {
            _isSaving.value = true
            val result = repository.updateSavedPlaceState(
                userId = currentUserId,
                placeId = placeId,
                personalNote = note,
                userRating = rating
            )
            _isSaving.value = false
            if (result.isSuccess) {
                _statusMessage.value = "Travel note & rating saved."
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Could not save note."
            }
        }
    }

    fun savePlaceUpdate(
        place: LucknowPlace,
        publishGlobally: Boolean,
        onComplete: (Boolean) -> Unit = {}
    ) {
        viewModelScope.launch {
            _isSaving.value = true
            val result = if (publishGlobally && isOwnerAdmin) {
                repository.saveGlobalPlace(place, userEmail)
            } else {
                repository.saveCustomPlace(currentUserId, place, userEmail)
            }
            _isSaving.value = false
            if (result.isSuccess) {
                _statusMessage.value = if (publishGlobally && isOwnerAdmin) {
                    "Published '${place.name}' live for all visitors!"
                } else {
                    "Saved '${place.name}' to your personal Lucknow guide."
                }
                onComplete(true)
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Failed to save place."
                onComplete(false)
            }
        }
    }

    fun deletePlaceUpdate(place: LucknowPlace, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            _isSaving.value = true
            val isCustom = place.sourceLabel == "My Update"
            val result = when {
                isCustom -> repository.deleteCustomPlace(currentUserId, place.id)
                isOwnerAdmin -> repository.deleteGlobalPlace(place.id)
                else -> Result.failure(IllegalStateException("Only the guide owner can delete global places."))
            }
            _isSaving.value = false
            if (result.isSuccess) {
                _statusMessage.value = "Removed '${place.name}'."
                onComplete()
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Unable to delete place."
            }
        }
    }

    fun syncBundledCatalogToCloud() {
        if (!isOwnerAdmin) {
            _errorMessage.value = "Sign in with owner account to publish global catalog updates."
            return
        }
        viewModelScope.launch {
            _isSyncingCloud.value = true
            val result = repository.seedCatalogToCloud(LucknowCatalogSeed.allPlaces, userEmail)
            _isSyncingCloud.value = false
            if (result.isSuccess) {
                val count = result.getOrNull() ?: 0
                _statusMessage.value = "Synced $count Lucknow places live to Firestore!"
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Cloud sync failed."
            }
        }
    }

    fun getPlaceById(placeId: String): LucknowPlace? {
        return uiState.value.allPlaces.firstOrNull { it.id == placeId }
            ?: LucknowCatalogSeed.allPlaces.firstOrNull { it.id == placeId }
    }

    fun categoryCounts(): Map<String, Int> {
        val counts = mutableMapOf<String, Int>()
        PlaceCategory.entries.forEach { counts[it.label] = 0 }
        uiState.value.allPlaces.forEach { place ->
            counts[place.category] = (counts[place.category] ?: 0) + 1
        }
        return counts
    }

    private companion object {
        const val TAG = "LucknowViewModel"
    }
}
