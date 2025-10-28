package com.example.legioncommander.views

import androidx.compose.foundation.combinedClickable // <-- Make sure to import this
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import androidx.navigation.NavController // Make sure you have this
import com.example.legioncommander.data.CommandDeck
import com.example.legioncommander.viewmodels.DecksViewModel

@OptIn(ExperimentalFoundationApi::class) // Needed for combinedClickable
@Composable
fun CurrentDecksView(
    navController: NavController,
    viewModel: DecksViewModel = viewModel() // Get an instance of the ViewModel
    ) {
    // Get the list of saved decks directly from our repository
    val myDecks by viewModel.allDecks.collectAsState()

    var deckToDelete by remember { mutableStateOf<CommandDeck?>(null) }
    deckToDelete?.let { deck ->
        AlertDialog(
            onDismissRequest = { deckToDelete = null }, // Dismiss if user clicks outside
            title = { Text("Delete Deck") },
            text = { Text("Are you sure you want to permanently delete the deck \"${deck.name}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.delete(deck) // Tell ViewModel to delete
                        deckToDelete = null     // Close the dialog
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { deckToDelete = null }) { // Close the dialog
                    Text("Cancel")
                }
            }
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (myDecks.isEmpty()) {
            Text(
                text = "No decks created yet!",
                fontFamily = StarJediFontFamily,
                fontWeight = FontWeight.Bold,
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Loop through the list of actual deck objects
                items(myDecks) { deck ->
                    // --- CHANGE 3: Use a Card with combinedClickable ---
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier
                            .aspectRatio(1f)
                            .combinedClickable(
                                onClick = {
                                    // Regular click still navigates
                                    navController.navigate(Screen.DeckDetail.createRoute(deck.id))
                                },
                                onLongClick = {
                                    // Long click sets the state to show the dialog
                                    deckToDelete = deck
                                }
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = deck.name,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
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