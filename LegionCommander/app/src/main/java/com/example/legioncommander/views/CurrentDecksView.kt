package com.example.legioncommander.views

import DeckItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import androidx.navigation.NavController
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.viewmodels.DecksViewModel

@Composable
fun CurrentDecksView(
    navController: NavController,
    decksViewModel: DecksViewModel = viewModel()
)
{
    val commandDecks by decksViewModel.allDecks.collectAsState()
    val battleDecks by decksViewModel.allBattleDecks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // --- Display Command Decks ---
        Text(
            text = "Command Decks",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = StarJediFontFamily
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (commandDecks.isEmpty())
        {
            Text("No Command Decks created yet.")
        }
        else
        {
            LazyColumn(
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(commandDecks) { deck ->
                    DeckItem(
                        deckName = deck.name,
                        faction = deck.faction,
                        onDeckClicked = {
                            val route = Screen.DeckDetail.createRoute(deck.id)
                            navController.navigate(route)
                        },
                        onDeckDeleted = {
                            decksViewModel.deleteCommandDeck(deck)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Display Battle Decks ---
        Text(
            text = "Battle Decks",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = StarJediFontFamily
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (battleDecks.isEmpty())
        {
            Text("No Battle Decks created yet.")
        }
        else
        {
            LazyColumn(
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(battleDecks) { deck ->
                    DeckItem(
                        deckName = deck.name,
                        faction = Faction.BATTLE_DECK,
                        onDeckClicked = {
                            val route = Screen.BattleDeckDetail.createRoute(deck.id)
                            navController.navigate(route)
                        },
                        onDeckDeleted = {
                            decksViewModel.deleteBattleDeck(deck)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentDecksViewPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    LegionCommanderTheme {
        CurrentDecksView(navController = navController)    }
}