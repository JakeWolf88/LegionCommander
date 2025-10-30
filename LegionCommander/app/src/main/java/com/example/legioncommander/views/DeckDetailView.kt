package com.example.legioncommander.views

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.* // Important: Import from material for swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.data.CommandCard
import com.example.legioncommander.data.CommandCardRepository
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodels.DeckDetailViewModel
import kotlin.math.roundToInt

private enum class SwipeState { NORMAL, USED }

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class) // Add ExperimentalMaterialApi
@Composable
fun DeckDetailView(
    deckId: Int,
    viewModel: DeckDetailViewModel = viewModel()
) {
    LaunchedEffect(deckId) {
        viewModel.loadDeck(deckId)
    }
    val deck by viewModel.deck.collectAsState()
    val allCards = CommandCardRepository.getAllCards()
    val currentDeck = deck

    val usedCardIds = remember { mutableStateListOf<String>() }
    var isZoomed by remember { mutableStateOf(false) }

    if (currentDeck == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading Deck...", fontFamily = StarJediFontFamily)
        }
        return
    }

    val cardsInDeck = allCards.filter { card -> currentDeck.cardIds.contains(card.id) }
        .sortedBy { it.pips }

    val pagerState = rememberPagerState(pageCount = { cardsInDeck.size })
    val horizontalPadding by animateDpAsState(if (isZoomed) 0.dp else 32.dp, label = "")

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentDeck.name,
            fontFamily = StarJediFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (isZoomed) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { isZoomed = false }
                )
            }

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = horizontalPadding),
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        enabled = !isZoomed,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { isZoomed = true }
            ) { pageIndex ->
                val card = cardsInDeck[pageIndex]
                val isUsed = usedCardIds.contains(card.id)

                // 2. Use our new SwipeableCard composable for cleaner code
                SwipeableCard(
                    card = card,
                    isUsed = isUsed,
                    onStateChange = {
                        // This lambda updates our central state list
                        if (isUsed) {
                            usedCardIds.remove(card.id)
                        } else {
                            usedCardIds.add(card.id)
                        }
                    }
                )
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


/**
 * A private composable that manages the swipe state for a single card.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeableCard(
    card: CommandCard,
    isUsed: Boolean,
    onStateChange: () -> Unit
) {
    val swipeDistance = -150.dp // How far up the card moves
    val swipeDistancePx = with(LocalDensity.current) { swipeDistance.toPx() }

    // Anchors define resting points: 0f for normal, and a negative pixel value for "up"
    val anchors = mapOf(0f to SwipeState.NORMAL, swipeDistancePx to SwipeState.USED)

    // The state object for the swipeable modifier
    val swipeableState = rememberSwipeableState(
        initialValue = if (isUsed) SwipeState.USED else SwipeState.NORMAL,
        confirmStateChange = {
            // This is called after a swipe. We trigger our state change here.
            onStateChange()
            true
        }
    )

    // This effect ensures if the state changes from elsewhere (e.g. a "reset" button),
    // the card animates to the correct position.
    LaunchedEffect(isUsed) {
        swipeableState.animateTo(if (isUsed) SwipeState.USED else SwipeState.NORMAL)
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(0.7f)
            .padding(vertical = 8.dp)
            // 4. The swipeable modifier itself
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Vertical,
                thresholds = { _, _ -> FractionalThreshold(0.5f) } // Swipe 50% to trigger
            )
            .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) }
    ) {
        Card(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = card.imageRes),
                contentDescription = card.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                colorFilter = if (isUsed) {
                    ColorFilter.tint(Color.Red.copy(alpha = 0.99f), BlendMode.Multiply)
                } else {
                    null
                }
            )
        }
    }
}
