package com.example.legioncommander.views.commandcards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.model.commandcards.CommandCard
import com.example.legioncommander.model.commandcards.CommandCardRepository
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodels.CommandDeckDetailViewModel
import kotlin.math.roundToInt

private enum class SwipeState { NORMAL, USED }

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CommandDeckDetailView(
    deckId: Int,
    viewModel: CommandDeckDetailViewModel = viewModel()
) {
    LaunchedEffect(deckId) {
        viewModel.loadDeck(deckId)
    }
    val deck by viewModel.deck.collectAsState()
    val allCards = CommandCardRepository.getAllCards()
    val usedCardIds = viewModel.usedCardIds
    var isZoomed by remember { mutableStateOf(false) }

    val currentDeck = deck ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading Deck...", fontFamily = StarJediFontFamily)
        }
        return
    }

    val cardsInDeck = allCards.filter { card -> currentDeck.cardIds.contains(card.id) }
        .sortedBy { it.pips }

    val pagerState = rememberPagerState(pageCount = { cardsInDeck.size })

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
            HorizontalPager(
                state = pagerState,
                // Added significant vertical padding to prevent bottom clipping
                contentPadding = PaddingValues(horizontal = 64.dp, vertical = 32.dp),
                pageSpacing = 24.dp,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                val card = cardsInDeck[pageIndex]
                val isUsed = usedCardIds.contains(card.id)

                SwipeableCard(
                    card = card,
                    isUsed = isUsed,
                    onDoubleClick = { isZoomed = true },
                    onStateChange = {
                        viewModel.toggleCardUsedState(card.id)
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

    if (isZoomed) {
        Dialog(
            onDismissRequest = { isZoomed = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            )
        ) {
            val currentCard = cardsInDeck[pagerState.currentPage]
            val isUsed = usedCardIds.contains(currentCard.id)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .combinedClickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isZoomed = false }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = currentCard.imageRes),
                    contentDescription = currentCard.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                    colorFilter = if (isUsed) {
                        ColorFilter.tint(Color.Red.copy(alpha = 0.99f), BlendMode.Multiply)
                    } else {
                        null
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun SwipeableCard(
    card: CommandCard,
    isUsed: Boolean,
    onDoubleClick: () -> Unit,
    onStateChange: () -> Unit
) {
    val swipeDistance = (-150).dp
    val swipeDistancePx = with(LocalDensity.current) { swipeDistance.toPx() }
    val anchors = mapOf(0f to SwipeState.NORMAL, swipeDistancePx to SwipeState.USED)

    val swipeableState = rememberSwipeableState(
        initialValue = if (isUsed) SwipeState.USED else SwipeState.NORMAL,
        confirmStateChange = { newState ->
            if ((newState == SwipeState.USED && !isUsed) || (newState == SwipeState.NORMAL && isUsed)) {
                onStateChange()
            }
            true
        }
    )

    LaunchedEffect(isUsed) {
        swipeableState.animateTo(if (isUsed) SwipeState.USED else SwipeState.NORMAL)
    }

    Box(
        contentAlignment = Alignment.TopCenter, // Changed to TopCenter to reclaim bottom space
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f) // Reduced from 0.9f to ensure the bottom edge is visible
                .aspectRatio(0.7f)
                .padding(vertical = 8.dp)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    orientation = Orientation.Vertical,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) }
                )
                .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .combinedClickable(
                        onClick = { /* Swipes handled by modifier */ },
                        onDoubleClick = onDoubleClick
                    )
            ) {
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
}
