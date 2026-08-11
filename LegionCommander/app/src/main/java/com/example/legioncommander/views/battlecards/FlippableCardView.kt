package com.example.legioncommander.views.battlecards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.legioncommander.R
import com.example.legioncommander.model.battlecards.BattleCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FlippableCardView(
    card: BattleCard,
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }
    var isZoomed by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "cardRotation"
    )

    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .width(280.dp)
                .height(400.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .combinedClickable(
                    onClick = { isFlipped = !isFlipped },
                    onDoubleClick = { isZoomed = true }
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            if (rotation < 90f) {
                CardBack()
            } else {
                CardFront(card = card, modifier = Modifier.graphicsLayer { rotationY = 180f })
            }
        }
    }

    if (isZoomed) {
        Dialog(
            onDismissRequest = { isZoomed = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f))
                    .combinedClickable(
                        onClick = { isZoomed = false },
                        onDoubleClick = { isZoomed = false }
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Determine if we show the front or back in the zoom based on current flip state
                if (!isFlipped) {
                    Image(
                        painter = painterResource(id = R.drawable.battle_deck_backside),
                        contentDescription = "Zoomed Card Back",
                        modifier = Modifier.fillMaxSize(0.9f),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Image(
                        painter = painterResource(id = card.imageRes),
                        contentDescription = "Zoomed Card Front",
                        modifier = Modifier.fillMaxSize(0.9f),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Composable
fun CardBack() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2d2d2d))
            .border(2.dp, Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.battle_deck_backside),
            contentDescription = "Card Back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun CardFront(card: BattleCard, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = card.imageRes),
            contentDescription = "Card Front",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
