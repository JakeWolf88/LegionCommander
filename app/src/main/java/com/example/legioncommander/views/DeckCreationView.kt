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
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legioncommander.data.CommandCardRepository
import com.example.legioncommander.data.Faction
import com.example.legioncommander.ui.theme.StarJediFontFamily

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
    val selectedCards = remember { mutableStateListOf<String>() }

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

                Card(
                    modifier = Modifier
                        .clickable {
                            // Toggle selection: add if not selected, remove if it is
                            if (isSelected) {
                                selectedCards.remove(card.id)
                            } else {
                                selectedCards.add(card.id)
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
