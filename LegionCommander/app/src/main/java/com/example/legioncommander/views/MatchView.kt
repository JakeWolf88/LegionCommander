package com.example.legioncommander.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.views.battlecards.BattleDeckDetailView
import com.example.legioncommander.views.commandcards.CommandDeckDetailView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchView(
    commandDeckId: Int,
    battleDeckId: Int,
    useDangerousEnvironments: Boolean
) {
    val scrollState = rememberScrollState()
    var showChat by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showChat = true },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Tactical Droid AI Chat"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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

        if (showChat) {
            ModalBottomSheet(
                onDismissRequest = { showChat = false },
                sheetState = sheetState,
                modifier = Modifier.fillMaxHeight(0.85f)
            ) {
                ChatView(onDismiss = { showChat = false })
            }
        }
    }
}
