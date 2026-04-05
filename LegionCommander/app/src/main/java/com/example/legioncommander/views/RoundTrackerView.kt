package com.example.legioncommander.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legioncommander.ui.theme.StarJediFontFamily

@Composable
fun RoundTrackerView(modifier: Modifier = Modifier) {
    var currentRound by remember { mutableIntStateOf(1) }
    var player1Points by remember { mutableIntStateOf(0) }
    var player2Points by remember { mutableIntStateOf(0) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Round Tracker ---
            Text(
                text = "Round",
                fontFamily = StarJediFontFamily,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                (1..5).forEach { round ->
                    RoundCircle(
                        round = round,
                        isSelected = currentRound == round,
                        onClick = { currentRound = round }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Victory Point Tracker ---
            Text(
                text = "Victory Points",
                fontFamily = StarJediFontFamily,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Blue Player
                PlayerScoreCounter(
                    label = "Blue Player",
                    score = player1Points,
                    onScoreChange = { player1Points = it.coerceIn(0, 12) }
                )

                // Red Player
                PlayerScoreCounter(
                    label = "Red Player",
                    score = player2Points,
                    onScoreChange = { player2Points = it.coerceIn(0, 12) }
                )
            }
        }
    }
}

@Composable
fun RoundCircle(round: Int, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = round.toString(),
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun PlayerScoreCounter(label: String, score: Int, onScoreChange: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onScoreChange(score - 1) }) {
                Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                text = score.toString(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            IconButton(onClick = { onScoreChange(score + 1) }) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}