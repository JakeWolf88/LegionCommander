package com.example.legioncommander.views

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * A centralized definition of all navigation routes in the app.
 * Using a sealed class ensures that we can only navigate to routes defined here.
 */
sealed class Screen(val route: String) {
    // Route for the initial screen that shows factions
    object Home : Screen("home")

    // Route for the deck creation screen. It requires a faction name.
    object DeckCreation : Screen("deck_creation/{factionName}") {
        /**
         * A helper function to build the correct route with the faction name included.
         * Usage: Screen.DeckCreation.createRoute("REBELS")
         */
        fun createRoute(factionName: String) = "deck_creation/$factionName"
    }

    // Route for the screen that shows the list of created decks.
    object CurrentDecks : Screen("current_decks")

    // Route for the detail view of a single deck. It requires a deck ID.
    object DeckDetail : Screen("deck_detail/{deckId}") {
        /**
         * A helper function to build the correct route with the deck ID included.
         * Usage: Screen.DeckDetail.createRoute(123)
         */
        fun createRoute(deckId: Int) = "deck_detail/$deckId"
    }
}
