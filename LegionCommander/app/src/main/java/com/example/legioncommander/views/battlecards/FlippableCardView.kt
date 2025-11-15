package com.example.legioncommander.views.battlecards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.legioncommander.R
import com.example.legioncommander.model.battlecards.BattleCard

@Composable
fun FlippableCardView(
    card: BattleCard,
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "cardRotation"
    )
    Card(
        modifier = modifier
            .width(280.dp)
            .height(400.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable { isFlipped = !isFlipped },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        if (rotation < 90f) {
            CardBack()
        } else {
            CardFront(card = card, modifier = Modifier.graphicsLayer { rotationY = 180f })
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
            painter = painterResource(id = R.drawable.battle_deck_backside), // Replace with your logo
            contentDescription = "Card Back",
            modifier = Modifier.fillMaxSize()
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = card.imageRes), // Replace with your logo
                contentDescription = "Card Front",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
