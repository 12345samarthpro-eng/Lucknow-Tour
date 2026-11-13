package com.example.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.EditLocationAlt
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.EditLocationAlt
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.R
import com.example.auth.GoogleAuthHelper
import com.example.auth.GoogleAuthHelper.authStateFlow
import com.example.data.repository.PlacesRepository
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.PlaceDetailScreen
import com.example.ui.screens.PlaceEditorScreen
import com.example.ui.screens.SavedItineraryScreen
import com.example.ui.screens.UpdateStudioScreen
import com.example.ui.viewmodel.LucknowViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

object LucknowRoutes {
    const val EXPLORE = "explore"
    const val FAVORITES = "favorites"
    const val SAVED = "saved"
    const val STUDIO = "studio"
    const val DETAIL = "detail/{placeId}"
    const val EDITOR = "editor?placeId={placeId}"

    fun detail(placeId: String): String = "detail/${Uri.encode(placeId)}"
    fun editor(placeId: String? = null): String {
        return if (placeId.isNullOrBlank()) "editor" else "editor?placeId=${Uri.encode(placeId)}"
    }
}

private data class TopLevelTab(
    val route: String,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val TopLevelTabs = listOf(
    TopLevelTab(
        route = LucknowRoutes.EXPLORE,
        labelRes = R.string.nav_explore,
        selectedIcon = Icons.Filled.Explore,
        unselectedIcon = Icons.Outlined.Explore
    ),
    TopLevelTab(
        route = LucknowRoutes.FAVORITES,
        labelRes = R.string.nav_favorites,
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    ),
    TopLevelTab(
        route = LucknowRoutes.SAVED,
        labelRes = R.string.nav_saved,
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.BookmarkBorder
    ),
    TopLevelTab(
        route = LucknowRoutes.STUDIO,
        labelRes = R.string.nav_studio,
        selectedIcon = Icons.Filled.EditLocationAlt,
        unselectedIcon = Icons.Outlined.EditLocationAlt
    )
)

@Composable
fun LucknowApp(auth: FirebaseAuth = Firebase.auth) {
    val currentUser by auth.authStateFlow().collectAsStateWithLifecycle(initialValue = auth.currentUser)
    val user = currentUser

    if (user == null) {
        AuthScreen(onAuthSuccess = {})
    } else {
        key(user.uid) {
            AuthenticatedLucknowShell(user = user)
        }
    }
}

@Composable
private fun AuthenticatedLucknowShell(user: FirebaseUser) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = remember(context) { CredentialManager.create(context) }
    val isOwnerAdmin = remember(user.email, user.isEmailVerified) {
        GoogleAuthHelper.isOwnerAdmin(user)
    }

    val viewModel: LucknowViewModel = viewModel(
        key = user.uid,
        factory = viewModelFactory {
            initializer {
                val app = checkNotNull(this[APPLICATION_KEY]) {
                    "APPLICATION_KEY missing from CreationExtras"
                }
                val databaseId = app.getString(R.string.firestore_database_id)
                val db = FirebaseFirestore.getInstance(databaseId)
                LucknowViewModel(
                    repository = PlacesRepository(db),
                    currentUserId = user.uid,
                    userEmail = user.email.orEmpty(),
                    displayName = user.displayName ?: "Lucknow Visitor",
                    userPhotoUrl = user.photoUrl?.toString().orEmpty(),
                    isOwnerAdmin = isOwnerAdmin
                )
            }
        }
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showTopLevelNav = currentRoute in TopLevelTabs.map { it.route }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.statusMessage) {
        val msg = uiState.statusMessage
        if (!msg.isNullOrBlank()) {
            snackbarHostState.showSnackbar(msg)
            viewModel.clearStatusMessage()
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        val err = uiState.errorMessage
        if (!err.isNullOrBlank()) {
            snackbarHostState.showSnackbar(err)
            viewModel.clearErrorMessage()
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 600.dp

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                if (showTopLevelNav && !useRail) {
                    NavigationBar {
                        TopLevelTabs.forEach { tab ->
                            val selected = currentRoute == tab.route
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(tab.route) {
                                        popUpTo(LucknowRoutes.EXPLORE) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = stringResource(tab.labelRes)
                                    )
                                },
                                label = { Text(stringResource(tab.labelRes)) }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (showTopLevelNav && useRail) {
                    NavigationRail {
                        TopLevelTabs.forEach { tab ->
                            val selected = currentRoute == tab.route
                            NavigationRailItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(tab.route) {
                                        popUpTo(LucknowRoutes.EXPLORE) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = stringResource(tab.labelRes)
                                    )
                                },
                                label = { Text(stringResource(tab.labelRes)) }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    NavHost(
                        navController = navController,
                        startDestination = LucknowRoutes.EXPLORE
                    ) {
                        composable(LucknowRoutes.EXPLORE) {
                            ExploreScreen(
                                uiState = uiState,
                                userName = user.displayName ?: "Traveler",
                                isOwnerAdmin = isOwnerAdmin,
                                categoryCounts = viewModel.categoryCounts(),
                                onSearchQueryChange = viewModel::onSearchQueryChange,
                                onCategorySelected = viewModel::onCategorySelected,
                                onQuickFilterSelected = viewModel::onQuickFilterSelected,
                                onSortSelected = viewModel::onSortSelected,
                                onPlaceClick = { id ->
                                    navController.navigate(LucknowRoutes.detail(id))
                                },
                                onFavoriteToggle = viewModel::toggleFavorite,
                                onBookmarkToggle = viewModel::toggleBookmark,
                                onAddPlaceClick = {
                                    navController.navigate(LucknowRoutes.editor(null))
                                },
                                onSignOutClick = {
                                    GoogleAuthHelper.signOut(credentialManager, scope)
                                }
                            )
                        }

                        composable(LucknowRoutes.FAVORITES) {
                            FavoritesScreen(
                                uiState = uiState,
                                onPlaceClick = { id ->
                                    navController.navigate(LucknowRoutes.detail(id))
                                },
                                onFavoriteToggle = viewModel::toggleFavorite,
                                onBookmarkToggle = viewModel::toggleBookmark,
                                onExploreClick = {
                                    navController.navigate(LucknowRoutes.EXPLORE) {
                                        popUpTo(LucknowRoutes.EXPLORE) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(LucknowRoutes.SAVED) {
                            SavedItineraryScreen(
                                uiState = uiState,
                                onPlaceClick = { id ->
                                    navController.navigate(LucknowRoutes.detail(id))
                                },
                                onFavoriteToggle = viewModel::toggleFavorite,
                                onBookmarkToggle = viewModel::toggleBookmark,
                                onExploreClick = {
                                    navController.navigate(LucknowRoutes.EXPLORE) {
                                        popUpTo(LucknowRoutes.EXPLORE) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(LucknowRoutes.STUDIO) {
                            UpdateStudioScreen(
                                uiState = uiState,
                                userEmail = user.email.orEmpty(),
                                isOwnerAdmin = isOwnerAdmin,
                                onAddNewPlace = {
                                    navController.navigate(LucknowRoutes.editor(null))
                                },
                                onEditPlace = { id ->
                                    navController.navigate(LucknowRoutes.editor(id))
                                },
                                onSyncCatalogToCloud = viewModel::syncBundledCatalogToCloud
                            )
                        }

                        composable(
                            route = LucknowRoutes.DETAIL,
                            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
                        ) { entry ->
                            val placeId = entry.arguments?.getString("placeId").orEmpty()
                            val place = viewModel.getPlaceById(placeId)
                            if (place != null) {
                                PlaceDetailScreen(
                                    place = place,
                                    savedPlace = uiState.savedMap[place.id],
                                    isSaving = uiState.isSaving,
                                    onBack = { navController.popBackStack() },
                                    onEditClick = {
                                        navController.navigate(LucknowRoutes.editor(place.id))
                                    },
                                    onToggleFavorite = { viewModel.toggleFavorite(place.id) },
                                    onToggleBookmark = { viewModel.toggleBookmark(place.id) },
                                    onToggleVisited = { viewModel.toggleVisited(place.id) },
                                    onSavePersonalNote = { note, rating ->
                                        viewModel.savePersonalNote(place.id, note, rating)
                                    }
                                )
                            } else {
                                LaunchedEffect(Unit) { navController.popBackStack() }
                            }
                        }

                        composable(
                            route = LucknowRoutes.EDITOR,
                            arguments = listOf(
                                navArgument("placeId") {
                                    type = NavType.StringType
                                    nullable = true
                                    defaultValue = null
                                }
                            )
                        ) { entry ->
                            val placeId = entry.arguments?.getString("placeId")
                            val existing = placeId?.let { viewModel.getPlaceById(it) }
                            PlaceEditorScreen(
                                existingPlace = existing,
                                isOwnerAdmin = isOwnerAdmin,
                                isSaving = uiState.isSaving,
                                onBack = { navController.popBackStack() },
                                onSave = { updated, publishGlobally ->
                                    viewModel.savePlaceUpdate(
                                        place = updated,
                                        publishGlobally = publishGlobally,
                                        onComplete = { ok ->
                                            if (ok) navController.popBackStack()
                                        }
                                    )
                                },
                                onDelete = { target ->
                                    viewModel.deletePlaceUpdate(target) {
                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
