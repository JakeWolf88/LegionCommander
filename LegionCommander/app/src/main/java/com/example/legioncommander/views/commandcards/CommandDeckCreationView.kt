package com.example.legioncommander.views.commandcards // Corrected package name based on file path

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
import com.example.legioncommander.model.commandcards.CommandCardRepository
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.model.commandcards.CommandDeck
import com.example.legioncommander.viewmodels.DecksViewModel

data class CommandCard(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val pips: Int
)

@Composable
fun CommandDeckCreationView(
    selectedFaction: Faction,
    viewModel: DecksViewModel = viewModel()

) {
    val cards = CommandCardRepository.getCardsForFaction(selectedFaction)    // A state to keep track of the IDs of selected cards
    val standingOrdersCardId = "gen4"
    val selectedCards = remember { mutableStateListOf(standingOrdersCardId) }
    val showNamePrompt = remember { mutableStateOf(false) }
    var deckName by remember { mutableStateOf("") }
    val cardsInDeck = cards.filter { selectedCards.contains(it.id) }
    val pips1 = cardsInDeck.count { it.pips == 1 }
    val pips2 = cardsInDeck.count { it.pips == 2 }
    val pips3 = cardsInDeck.count { it.pips == 3 }

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
            text = "Pips: [1s: $pips1/2] [2s: $pips2/2] [3s: $pips3/2]",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
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
                        val newDeck = CommandDeck(
                            name = deckName,
                            cardIds = selectedCards.toList(), // Convert the mutable list to a fixed list
                            faction = selectedFaction

                        )

                        viewModel.insertCommandDeck(newDeck)
                        showNamePrompt.value = false
                    },
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // We'll display 2 cards per row
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cards) { card ->
                // Check if the current card is selected
                val isSelected = selectedCards.contains(card.id)
                val isStandingOrders = card.id == standingOrdersCardId
                val pipLimitReached = cardsInDeck.count { it.pips == card.pips } >= 2
                val isSelectionDisabled = !isSelected && pipLimitReached && !isStandingOrders
                Card(
                    modifier = Modifier
                        // Disable clicks for the "Standing Orders" card
                        .clickable(enabled = !isStandingOrders) {    if (isSelected) {
                            selectedCards.remove(card.id)
                        } else {
                            // We look up the card objects from the repository based on the IDs currently in selectedCards
                            val cardsInDeck = cards.filter { selectedCards.contains(it.id) }
                            val samePipCount = cardsInDeck.count { it.pips == card.pips }

                            if (samePipCount < 2 && selectedCards.size < 7) {
                                selectedCards.add(card.id)
                            }
                        }
                        }
                        // Add a colored border if the card is selected
                        .alpha(if (isSelectionDisabled) 0.5f else 1f) // Dim the card if it's disabled
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
                                .clip(RoundedCornerShape(8.dp))
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
        CommandDeckCreationView(selectedFaction = Faction.SEPARATISTS)
    }
}
