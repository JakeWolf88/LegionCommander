
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.legioncommander.data.CommandCardRepository // Import the repository
import com.example.legioncommander.data.Faction

@Composable
fun DeckCreationView(factionName: Faction) {
    var deckName by remember { mutableStateOf("") }

    // Convert the incoming string from the route into a Faction enum object.
    // This is safer to work with.
    val selectedFaction = factionName

    // --- THIS IS THE KEY PART ---
    // Fetch the list of cards for the selected faction ONCE.
    // `remember` ensures this list isn't re-fetched on every recomposition.
    val cardList by remember {
        mutableStateOf(CommandCardRepository.getCardsForFaction(selectedFaction))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ... (Your existing Text and TextField for the deck name)

        // --- Display the list of cards ---
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(cardList) { card ->
                // This is a simple display, you can make it much fancier
                Text(
                    text = "${card.name} (${card.pip} Pip)",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
