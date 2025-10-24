package com.example.legioncommander.views // Corrected package name based on file path

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legioncommander.data.CommandCardRepository
import com.example.legioncommander.data.Faction
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.legioncommander.data.CommandDeck
import com.example.legioncommander.data.DeckRepository

// 1. Data class to represent a command card
data class CommandCard(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val pips: Int
)

@Composable
fun DeckCreationView(selectedFaction: Faction) { // Changed parameter name for clarity
    val cards = CommandCardRepository.getCardsForFaction(selectedFaction)    // A state to keep track of the IDs of selected cards
    val standingOrdersCardId = "gen4"
    val selectedCards = remember { mutableStateListOf(standingOrdersCardId) }
    val showNamePrompt = remember { mutableStateOf(false) }
    var deckName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Your ${selectedFaction.name} Deck",
            fontFamily = StarJediFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "Selected Cards: ${selectedCards.size}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (selectedCards.size == 7)
        {
            Button(

                onClick = { showNamePrompt.value = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
            ){
                Text("Create Deck")
            }
        }

        // --- Deck Naming Dialog ---
        if (showNamePrompt.value) {
            AlertDialog(
                onDismissRequest = {
                    // Close the dialog if the user clicks outside of it
                    showNamePrompt.value = false
                    deckName = "" // Reset name
                },
                title = { Text("Name Your Deck") },
                text = {
                    TextField(
                        value = deckName,
                        onValueChange = { deckName = it },
                        label = { Text("Deck Name") },
                        singleLine = true
                    )
                },
                confirmButton = {
                    TextButton(    onClick = {
                        // 1. Create the new CommandDeck object
                        val newDeck = CommandDeck(
                            name = deckName,
                            cardIds = selectedCards.toList(), // Convert the mutable list to a fixed list
                            faction = selectedFaction
                        )

                        // 2. Save it to the repository
                        DeckRepository.saveDeck(newDeck)

                        showNamePrompt.value = false
                        // TODO: Navigate to the decks list or show a success message
                    },
                        // Only enable the save button if a name has been entered
                        enabled = deckName.isNotBlank()
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showNamePrompt.value = false
                            deckName = "" // Reset name
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // The selectable grid of cards
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // We'll display 2 cards per row
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cards) { card ->
                // Check if the current card is selected
                val isSelected = selectedCards.contains(card.id)
                val isStandingOrders = card.id == standingOrdersCardId

                Card(
                    modifier = Modifier
                        // Disable clicks for the "Standing Orders" card
                        .clickable(enabled = !isStandingOrders) {
                            if (isSelected) {
                                selectedCards.remove(card.id)
                            } else {
                                if (selectedCards.size < 7) {
                                    selectedCards.add(card.id)
                                }
                            }
                        }
                        // Add a colored border if the card is selected
                        .border(
                            width = 3.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = card.imageRes),
                            contentDescription = card.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.7f) // Aspect ratio for a card
                                .clip(RoundedCornerShape(8.dp)) // Corrected the clip import
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = card.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeckCreationViewPreview() {
    LegionCommanderTheme {
        DeckCreationView(selectedFaction = Faction.SEPARATISTS)
    }
}
