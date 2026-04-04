package com.example.legioncommander.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.views.battlecards.BattleDeckDetailView
import com.example.legioncommander.views.commandcards.CommandDeckDetailView

@Composable
fun MatchView(
    commandDeckId: Int,
    battleDeckId: Int,
    useDangerousEnvironments: Boolean
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Match Progress",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = StarJediFontFamily,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //Score Card
        Text(
            text = "Score",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = StarJediFontFamily,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(modifier = Modifier.height(400.dp)) {
            RoundTrackerView()
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Battle Deck
        Text(
            text = "Battle Deck",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = StarJediFontFamily,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(modifier = Modifier.height(400.dp)) {
            BattleDeckDetailView(deckId = battleDeckId)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Command Deck
        Text(
            text = "Command Deck",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = StarJediFontFamily,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(modifier = Modifier.height(500.dp)) {
            CommandDeckDetailView(deckId = commandDeckId)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Dangerous Environments on Bottom (Conditional)
        if (useDangerousEnvironments) {
            Text(
                text = "Dangerous Environments",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = StarJediFontFamily,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Box(modifier = Modifier.height(300.dp)) {
                DangerousEnvironmentsView()
            }
        }
    }
}