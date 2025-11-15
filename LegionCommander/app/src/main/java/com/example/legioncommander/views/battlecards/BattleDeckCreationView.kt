package com.example.legioncommander.views.battlecards

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.model.battlecards.BattleCardRepository
import com.example.legioncommander.model.battlecards.BattleCardType
import com.example.legioncommander.model.battlecards.BattleDeck
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodels.DecksViewModel

@Composable
fun BattleDeckCreationView()
{
    val cards = BattleCardRepository.getAllCards()
    val selectedPrimaryCards = remember { mutableStateListOf<String>() }
    val selectedSecondaryCards = remember { mutableStateListOf<String>() }
    val selectedAdvantageCards = remember { mutableStateListOf<String>() }
    val showNamePrompt = remember { mutableStateOf(false) }
    var deckName by remember { mutableStateOf("") }
    val decksViewModel: DecksViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Your Battle Deck",
            fontFamily = StarJediFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "Selected Primary: ${selectedPrimaryCards.size} Secondary: ${selectedSecondaryCards.size} Advantage: ${selectedAdvantageCards.size} Cards",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        if (selectedPrimaryCards.size == 3 && selectedSecondaryCards.size == 3 && selectedAdvantageCards.size == 3)
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
                    deckName = ""
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
                        val newBattleDeck = BattleDeck(
                            name = deckName,
                            primaryCardIds =  selectedPrimaryCards.toList(),
                            secondaryCardIds =  selectedSecondaryCards.toList(),
                            advantageCardIds =  selectedAdvantageCards.toList()
                        )
                        decksViewModel.insertBattleDeck(newBattleDeck)
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // We'll display 2 cards per row
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cards) { card ->
                val isPrimarySelected = selectedPrimaryCards.contains(card.id)
                val isSecondarySelected = selectedSecondaryCards.contains(card.id)
                val isAdvantageSelected = selectedAdvantageCards.contains(card.id)
                val isSelected = isPrimarySelected || isSecondarySelected || isAdvantageSelected

                Card(
                    modifier = Modifier
                        // Disable clicks for the "Standing Orders" card
                        .clickable {
                            // 2. When a card is clicked, first check its type to decide which list to modify.
                            when (card.cardType)
                            {
                                BattleCardType.PRIMARY ->
                                {
                                    if (isPrimarySelected)
                                    {
                                        selectedPrimaryCards.remove(card.id)
                                    }
                                    else if (selectedPrimaryCards.size < 3)
                                    {
                                        selectedPrimaryCards.add(card.id)
                                    }
                                }

                                BattleCardType.SECONDARY ->
                                {
                                    if (isSecondarySelected)
                                    {
                                        selectedSecondaryCards.remove(card.id)
                                    }
                                    else if (selectedSecondaryCards.size < 3)
                                    {
                                        selectedSecondaryCards.add(card.id)
                                    }
                                }

                                BattleCardType.ADVANTAGE ->
                                {
                                    if (isAdvantageSelected)
                                    {
                                        selectedAdvantageCards.remove(card.id)
                                    }
                                    else if (selectedAdvantageCards.size < 3)
                                    {
                                        selectedAdvantageCards.add(card.id)
                                    }
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
                        val imageAspectRatio = when (card.cardType) {
                            BattleCardType.SECONDARY, BattleCardType.ADVANTAGE -> 0.7f
                            BattleCardType.PRIMARY -> 0.5f // Landscape (wider than it is tall)
                        }
                        Image(
                            painter = painterResource(id = card.imageRes),
                            contentDescription = card.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(imageAspectRatio) // Aspect ratio for a card
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