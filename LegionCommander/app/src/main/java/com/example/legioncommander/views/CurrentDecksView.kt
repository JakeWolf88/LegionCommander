package com.example.legioncommander.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.data.DeckRepository
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily
import androidx.navigation.NavController // Make sure you have this
import com.example.legioncommander.viewmodels.DecksViewModel

@Composable
fun CurrentDecksView(
    navController: NavController,
    viewModel: DecksViewModel = viewModel() // Get an instance of the ViewModel
    ) {
    // Get the list of saved decks directly from our repository
    val myDecks by viewModel.allDecks.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (myDecks.isEmpty()) {
            Text(
                text = "No decks created yet!",
                fontFamily = StarJediFontFamily,
                fontWeight = FontWeight.Bold,
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Loop through the list of actual deck objects
                items(myDecks) { deck ->
                    Button(
                        onClick = {
                            navController.navigate(Screen.DeckDetail.createRoute(deck.id))                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 1f)
                    ) {
                        // Use the real deck name for the button label
                        Text(text = deck.name)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentDecksViewPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    LegionCommanderTheme {
        CurrentDecksView(navController = navController)    }
}