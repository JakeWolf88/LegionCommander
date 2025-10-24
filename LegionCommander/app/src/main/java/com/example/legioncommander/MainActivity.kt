package com.example.legioncommander

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.legioncommander.data.Faction
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.views.CurrentDecksView
import com.example.legioncommander.views.DeckBuilderView
import com.example.legioncommander.views.DeckCreationView
import com.example.legioncommander.views.DeckDetailView

// Sealed class to define the navigation routes for our screens
sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object DeckBuilder : Screen("deck_builder", "Deck Builder", Icons.Default.Build)
    object MyDecks : Screen("my_decks", "My Decks", Icons.Default.List)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object DeckCreation : Screen("deck_creation/{factionName}", "Deck Creation", Icons.Default.Build) {
        // Helper function to create the correct route for a specific faction
        fun createRoute(factionName: String) = "deck_creation/$factionName"
    }
    // --- CHANGE 1: Add the DeckDetail route definition ---
    object DeckDetail : Screen("deck_detail/{deckId}", "Deck Detail", Icons.Default.List) {
        fun createRoute(deckId: Int) = "deck_detail/$deckId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LegionCommanderTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // NavController manages the navigation within a NavHost. [1]
    val navController = rememberNavController()

    // List of our main screens
    val items = listOf(
        Screen.DeckBuilder,
        Screen.MyDecks,
        Screen.Settings,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                // Get the current back stack entry to determine the current route. [2]
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost is a container that displays the current destination. [13]
        NavHost(navController, startDestination = Screen.DeckBuilder.route, Modifier.padding(innerPadding)) {
            composable(Screen.DeckBuilder.route) {
                // Call your new composable from the other file
                DeckBuilderView(navController)
            }
            composable(Screen.MyDecks.route) {
                CurrentDecksView(navController = navController)
            }
            composable(Screen.Settings.route) { SettingsScreen() }
            composable(
                route = Screen.DeckCreation.route,
                arguments = listOf(navArgument("factionName") { type = NavType.StringType })
            ) { backStackEntry ->
                val factionName = backStackEntry.arguments?.getString("factionName")
                // It's safer to convert the String to the Enum here
                val selectedFaction = factionName?.let { Faction.valueOf(it) }

                if (selectedFaction != null) {
                    DeckCreationView(selectedFaction = selectedFaction)
                } else {
                    // If faction is null for some reason, just go back
                    navController.popBackStack()
                }
            }
            composable(
                route = Screen.DeckDetail.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Retrieve the deckId argument from the route
                val deckId = backStackEntry.arguments?.getInt("deckId")
                if (deckId != null) {
                    DeckDetailView(deckId = deckId)
                } else {
                    // If the ID is missing for some reason, go back.
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen", fontFamily = StarJediFontFamily, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LegionCommanderTheme {
        MainScreen()
    }
}
