package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui.theme.FavoriteHeartRed
import com.example.ui.viewmodel.LucknowUiState

@Composable
fun FavoritesScreen(
    uiState: LucknowUiState,
    onPlaceClick: (String) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val favoritePlaces = remember(uiState.allPlaces, uiState.savedMap) {
        uiState.allPlaces
            .filter { uiState.savedMap[it.id]?.isFavorite == true }
            .sortedByDescending { it.rating }
    }

    val categoriesInFavorites = remember(favoritePlaces) {
        favoritePlaces.map { it.category }.distinct().sorted()
    }

    val displayedFavorites = remember(favoritePlaces, selectedCategory) {
        if (selectedCategory == null || categoriesInFavorites.none { it == selectedCategory }) {
            favoritePlaces
        } else {
            favoritePlaces.filter { it.category == selectedCategory }
        }
    }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val columnCount = when {
                maxWidth >= 900.dp -> 3
                maxWidth >= 600.dp -> 2
                else -> 1
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columnCount),
                contentPadding = PaddingValues(bottom = 96.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("favorites_grid")
            ) {
                item(span = { GridItemSpan(columnCount) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "My Favorite Places",
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Card(
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(14.dp),
                                        color = FavoriteHeartRed.copy(alpha = 0.14f)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = null,
                                            tint = FavoriteHeartRed,
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .size(24.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = if (favoritePlaces.size == 1) {
                                                "1 Favorite Place"
                                            } else {
                                                "${favoritePlaces.size} Favorite Places"
                                            },
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Synced across devices in Cloud Firestore",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }

                        if (categoriesInFavorites.size > 1) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                item {
                                    FilterChip(
                                        selected = selectedCategory == null,
                                        onClick = { selectedCategory = null },
                                        label = { Text("All (${favoritePlaces.size})") },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                        )
                                    )
                                }
                                items(categoriesInFavorites) { category ->
                                    val count = favoritePlaces.count { it.category == category }
                                    val selected = selectedCategory == category
                                    FilterChip(
                                        selected = selected,
                                        onClick = {
                                            selectedCategory = if (selected) null else category
                                        },
                                        label = { Text("$category ($count)") },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                if (displayedFavorites.isEmpty()) {
                    item(span = { GridItemSpan(columnCount) }) {
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 720.dp)
                                .padding(horizontal = 16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(28.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    tint = FavoriteHeartRed,
                                    modifier = Modifier.size(48.dp)
                                )
                                Text(
                                    text = "No favorites yet",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Tap the heart icon on any Lucknow monument, restaurant, park, or market to save your favorites here.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Button(
                                    onClick = onExploreClick,
                                    modifier = Modifier.testTag("favorites_explore_button")
                                ) {
                                    Text("Explore Lucknow Places")
                                }
                            }
                        }
                    }
                } else {
                    items(
                        items = displayedFavorites,
                        key = { it.id }
                    ) { place ->
                        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                            PlaceListCard(
                                place = place,
                                savedPlace = uiState.savedMap[place.id],
                                onClick = { onPlaceClick(place.id) },
                                onFavoriteToggle = { onFavoriteToggle(place.id) },
                                onBookmarkToggle = { onBookmarkToggle(place.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
