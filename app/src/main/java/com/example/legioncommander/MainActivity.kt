package com.example.legioncommander

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
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
import com.example.legioncommander.views.DeckBuilderView
import com.example.legioncommander.views.DeckCreationView

// Sealed class to define the navigation routes for our screens
sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object DeckBuilder : Screen("deck_builder", "Deck Builder", Icons.Default.Build)
    object MyDecks : Screen("my_decks", "My Decks", Icons.Default.List)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object DeckCreation : Screen("deck_creation/{factionName}", "Deck Creation", Icons.Default.Build) {
        // Helper function to create the correct route for a specific faction
        fun createRoute(factionName: String) = "deck_creation/$factionName"
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
            composable(Screen.MyDecks.route) { MyDecksScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
            composable(
                route = Screen.DeckCreation.route,
                arguments = listOf(navArgument("factionName") { type = NavType.StringType })
            ) { backStackEntry ->
                val factionName = backStackEntry.arguments?.getString("factionName")
                if (factionName != null) {
                    val selectedFaction = Faction.valueOf(factionName)
                    // This now calls the powerful Composable in your new file
                    DeckCreationView(selectedFaction)
                } else {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun MyDecksScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "My Decks Screen", fontFamily = StarJediFontFamily, fontWeight = FontWeight.Bold)
    }
    @Composable
    fun DeckBuilderView() {
        // A list of the deck names for the buttons.
        val deckButtonLabels = listOf(
            "Rebels Command Deck",
            "Republic Command Deck",
            "CIS Command Deck",
            "Empire Command Deck"
        )

        // Use a LazyVerticalGrid to arrange items in a 2x2 grid.
        LazyVerticalGrid(
            // We specify that we want 2 columns.
            columns = GridCells.Fixed(2),
            // Add some padding around the whole grid.
            modifier = Modifier.padding(16.dp),
            // Add spacing between the grid items, both horizontally and vertically.
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Loop through the list of labels.
            items(deckButtonLabels) { label ->
                // A rectangular button.
                Button(
                    onClick = { /* TODO: Handle button click */ },
                    modifier = Modifier
                        // The button will fill the width of its grid cell.
                        .fillMaxWidth()
                        // We maintain a rectangular aspect ratio.
                        .aspectRatio(2f / 1f)
                ) {
                    Text(text = label)
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
