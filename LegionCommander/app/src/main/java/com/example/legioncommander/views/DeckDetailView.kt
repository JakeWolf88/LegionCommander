package com.example.legioncommander.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.legioncommander.data.CommandCardRepository
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodels.DeckDetailViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeckDetailView(
    deckId: Int,
    // Instantiate the ViewModel for this screen
    viewModel: DeckDetailViewModel = viewModel()
) {
    // --- THIS IS THE FIX ---
    // 1. Trigger the data load when the view is first composed (or if deckId changes)
    LaunchedEffect(deckId) {
        viewModel.loadDeck(deckId)
    }

    // 2. Collect the deck from the ViewModel's StateFlow.
    // `collectAsState` makes your UI reactively update when the data is loaded.
    val deck by viewModel.deck.collectAsState()

    // --- THE REST OF YOUR CODE STAYS LARGELY THE SAME ---
    val allCards = CommandCardRepository.getAllCards()

    // Use a local variable for the deck to handle the initial null state.
    val currentDeck = deck

    if (currentDeck == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // You can add a loading indicator here if you want
            Text("Loading Deck...", fontFamily = StarJediFontFamily)
        }
        return
    }

    val cardsInDeck = allCards.filter { card -> currentDeck.cardIds.contains(card.id) }
        .sortedBy { it.pips }

    val pagerState = rememberPagerState(pageCount = { cardsInDeck.size })
    var isZoomed by remember { mutableStateOf(false) }
    val horizontalPadding by animateDpAsState(
        targetValue = if (isZoomed) 0.dp else 32.dp,
        label = "Horizontal Padding Animation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentDeck.name,
            fontFamily = StarJediFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(Modifier.height(8.dp))

        // --- CHANGE 3: Wrap Pager in a Box for the background overlay ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            // Semi-transparent background that appears when zoomed
            if (isZoomed) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .clickable( // Also allow clicking the background to unzoom
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // No ripple effect
                        ) { isZoomed = false }
                )
            }

            HorizontalPager(
                state = pagerState,
                // Use the animated padding
                contentPadding = PaddingValues(horizontal = horizontalPadding),
                modifier = Modifier
                    .fillMaxSize()
                    // --- CHANGE 4: Toggle zoom on click ---
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { isZoomed = !isZoomed }
            ) { pageIndex ->
                val card = cardsInDeck[pageIndex]
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(0.7f)
                        .padding(vertical = 8.dp) // Keep some vertical padding
                        .animateContentSize() // Animates size changes within the card
                ) {
                    Image(
                        painter = painterResource(id = card.imageRes),
                        contentDescription = card.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "${pagerState.currentPage + 1} / ${cardsInDeck.size}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(24.dp))
    }
}
