package com.example.legioncommander.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.legioncommander.Screen
import com.example.legioncommander.model.battlecards.BattleDeck
import com.example.legioncommander.model.commandcards.CommandDeck
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodels.DecksViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartMatchView(
    navController: NavController,
    decksViewModel: DecksViewModel = viewModel()
) {
    val commandDecks by decksViewModel.allDecks.collectAsState()
    val battleDecks by decksViewModel.allBattleDecks.collectAsState()

    var selectedCommandDeck by remember { mutableStateOf<CommandDeck?>(null) }
    var selectedBattleDeck by remember { mutableStateOf<BattleDeck?>(null) }
    var useDangerousEnvironments by remember { mutableStateOf(false) }

    var deckToDelete by remember { mutableStateOf<Any?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Start Match",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = StarJediFontFamily
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        // Select Command Deck
        Text(
            text = "Select Command Deck",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        Box(modifier = Modifier.weight(1f)) {
            if (commandDecks.isEmpty()) {
                Text("No Command Decks available.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(commandDecks) { deck ->
                        SelectableDeckItem(
                            name = deck.name,
                            isSelected = selectedCommandDeck?.id == deck.id,
                            onClick = { selectedCommandDeck = deck },
                            onLongClick = {
                                deckToDelete = deck
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Select Battle Deck
        Text(
            text = "Select Battle Deck",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        Box(modifier = Modifier.weight(1f)) {
            if (battleDecks.isEmpty()) {
                Text("No Battle Decks available.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(battleDecks) { deck ->
                        SelectableDeckItem(
                            name = deck.name,
                            isSelected = selectedBattleDeck?.id == deck.id,
                            onClick = { selectedBattleDeck = deck },
                            onLongClick = {
                                deckToDelete = deck
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { useDangerousEnvironments = !useDangerousEnvironments }
        ) {
            Checkbox(
                checked = useDangerousEnvironments,
                onCheckedChange = { useDangerousEnvironments = it }
            )
            Text(text = "Use Dangerous Environments")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedCommandDeck?.let { commandDeck ->
                    selectedBattleDeck?.let { battleDeck ->
                        navController.navigate(
                            Screen.Match.createRoute(
                                commandDeckId = commandDeck.id,
                                battleDeckId = battleDeck.id,
                                useDangerous = useDangerousEnvironments
                            )
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedCommandDeck != null && selectedBattleDeck != null
        ) {
            Text("Start Match")
        }
    }

    if (showDeleteDialog && deckToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Deck") },
            text = { 
                val name = when (val deck = deckToDelete) {
                    is CommandDeck -> deck.name
                    is BattleDeck -> deck.name
                    else -> "this deck"
                }
                Text("Are you sure you want to delete '$name'?") 
            },
            confirmButton = {
                TextButton(onClick = {
                    when (val deck = deckToDelete) {
                        is CommandDeck -> decksViewModel.deleteCommandDeck(deck)
                        is BattleDeck -> decksViewModel.deleteBattleDeck(deck)
                    }
                    showDeleteDialog = false
                    deckToDelete = null
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectableDeckItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            if (isSelected) {
                RadioButton(selected = true, onClick = null)
            }
        }
    }
}
