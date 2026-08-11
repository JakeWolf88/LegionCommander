package com.example.legioncommander

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.ui.theme.icons.Playing_cards
import com.example.legioncommander.viewmodels.ChatViewModel
import com.example.legioncommander.views.battlecards.BattleDeckCreationView
import com.example.legioncommander.views.DeckBuilderView
import com.example.legioncommander.views.StartMatchView
import com.example.legioncommander.views.MatchView
import com.example.legioncommander.views.commandcards.CommandDeckCreationView
import com.example.legioncommander.views.commandcards.CommandDeckDetailView
import com.example.legioncommander.views.battlecards.BattleDeckDetailView
import kotlinx.coroutines.launch

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object DeckBuilder : Screen("deck_builder", "Deck Builder", Icons.Default.Home)
    object MyDecks : Screen("my_decks", "Match Start", Playing_cards)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object DeckCreation : Screen("deck_creation/{factionName}", "Deck Creation", Icons.Default.Build) {
        fun createRoute(factionName: String) = "deck_creation/$factionName"
    }
    object BattleDeckCreation : Screen("battle_deck_creation/{factionName}", "Deck Creation", Icons.Default.Build) {
        fun createRoute(factionName: String) = "deck_creation/$factionName"
    }
    object DeckDetail : Screen("deck_detail/{deckId}", "Deck Detail", Icons.Default.List) {
        fun createRoute(deckId: Int) = "deck_detail/$deckId"
    }
    object BattleDeckDetail : Screen("battle_deck_detail/{deckId}", "Battle Deck", Icons.Default.List) {
        fun createRoute(deckId: Int) = "battle_deck_detail/$deckId"
    }
    object Match : Screen("match/{commandDeckId}/{battleDeckId}/{useDangerous}", "Match", Icons.Default.List) {
        fun createRoute(commandDeckId: Int, battleDeckId: Int, useDangerous: Boolean) =
            "match/$commandDeckId/$battleDeckId/$useDangerous"
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
    val navController = rememberNavController()
    val items = listOf(Screen.DeckBuilder, Screen.MyDecks, Screen.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val topLevelScreens = listOf(Screen.DeckBuilder.route, Screen.MyDecks.route, Screen.Settings.route)
    val shouldShowBottomBar = currentDestination?.route in topLevelScreens

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar) {
                NavigationBar {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.DeckBuilder.route, Modifier.padding(innerPadding)) {
            composable(Screen.DeckBuilder.route) { DeckBuilderView(navController) }
            composable(Screen.MyDecks.route) { StartMatchView(navController = navController) }
            composable(Screen.Settings.route) { SettingsScreen() }
            composable(
                route = Screen.DeckCreation.route,
                arguments = listOf(navArgument("factionName") { type = NavType.StringType })
            ) { backStackEntry ->
                val factionName = backStackEntry.arguments?.getString("factionName")
                val selectedFaction = factionName?.let { Faction.valueOf(it) }
                if (selectedFaction != null) {
                    CommandDeckCreationView(selectedFaction = selectedFaction)
                } else {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screen.DeckDetail.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId")
                if (deckId != null) {
                    CommandDeckDetailView(deckId = deckId)
                } else {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screen.BattleDeckCreation.route,
                arguments = listOf(navArgument("factionName") { type = NavType.StringType })
            ) { backStackEntry ->
                val factionName = backStackEntry.arguments?.getString("factionName")
                if (factionName != null) {
                    BattleDeckCreationView()
                } else {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screen.BattleDeckDetail.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId")
                requireNotNull(deckId) { "deckId parameter was not found." }
                BattleDeckDetailView(deckId = deckId)
            }
            composable(
                route = Screen.Match.route,
                arguments = listOf(
                    navArgument("commandDeckId") { type = NavType.IntType },
                    navArgument("battleDeckId") { type = NavType.IntType },
                    navArgument("useDangerous") { type = NavType.BoolType }
                )
            ) { backStackEntry ->
                val commandDeckId = backStackEntry.arguments?.getInt("commandDeckId") ?: 0
                val battleDeckId = backStackEntry.arguments?.getInt("battleDeckId") ?: 0
                val useDangerous = backStackEntry.arguments?.getBoolean("useDangerous") ?: false
                MatchView(
                    commandDeckId = commandDeckId,
                    battleDeckId = battleDeckId,
                    useDangerousEnvironments = useDangerous
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(viewModel: ChatViewModel = viewModel()) {
    val storedApiKey by viewModel.userApiKey.collectAsState(initial = "")
    var tempKey by remember(storedApiKey) { mutableStateOf(storedApiKey ?: "") }
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings",
                fontFamily = StarJediFontFamily,
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Gemini AI Configuration",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Paste your key from the 'API key' column in AI Studio.",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    OutlinedTextField(
                        value = tempKey,
                        onValueChange = { tempKey = it },
                        label = { Text("API Key") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { 
                            viewModel.saveApiKey(tempKey)
                            scope.launch {
                                snackbarHostState.showSnackbar("Key Saved! You can now use the Analyzer.")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save & Update Droid")
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Troubleshooting:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "1. Enable 'Generative Language API' in Cloud Console.\n" +
                               "2. Wait 5 minutes for Google to activate the key.\n" +
                               "3. If 404 persists, try a fresh key from a NEW project in AI Studio.",
                        style = MaterialTheme.typography.bodySmall,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { uriHandler.openUri("https://aistudio.google.com/app/apikey") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Key, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Open AI Studio")
            }
        }
    }
}
