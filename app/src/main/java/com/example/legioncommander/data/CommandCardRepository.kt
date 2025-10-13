package com.example.legioncommander.data

import com.example.legioncommander.R
import com.example.legioncommander.data.CommandCard
import com.example.legioncommander.data.Faction

// A simple object to act as our card database/repository.
object CommandCardRepository {

    // A private list of ALL command cards in the game.
    // This is only created once when the Repository is first accessed.
    private val allCards = listOf(
        // --- REBEL CARDS ---
        CommandCard(name = "Ambush", pip = 1, faction = Faction.REBELS, picture = R.drawable.rebel_logo),
        CommandCard(name = "Push", pip = 1, faction = Faction.REBELS, picture = R.drawable.rebel_logo),
        CommandCard(name = "Assault", pip = 3, faction = Faction.REBELS, picture = R.drawable.rebel_logo),

        // --- EMPIRE CARDS ---
        CommandCard(name = "Implacable", pip = 1, faction = Faction.EMPIRE, picture = R.drawable.empire_logo),
        CommandCard(name = "Coordinated Fire", pip = 2, faction = Faction.EMPIRE, picture = R.drawable.empire_logo),
        CommandCard(name = "Master of Evil", pip = 3, faction = Faction.EMPIRE, picture = R.drawable.empire_logo),

        // --- REPUBLIC CARDS ---
        CommandCard(name = "Air Support", pip = 1, faction = Faction.REPUBLIC, picture = R.drawable.republic_logo),
        CommandCard(name = "Attack of the Clones", pip = 2, faction = Faction.REPUBLIC, picture = R.drawable.republic_logo),

        // --- CIS CARDS ---
        CommandCard(name = "Roger, Roger!", pip = 1, faction = Faction.SEPARATISTS, picture = R.drawable.cis_logo),
        CommandCard(name = "Mechanized Incursion", pip = 3, faction = Faction.SEPARATISTS, picture = R.drawable.cis_logo),

        //Shadow Collective Cards
        CommandCard(name = "Fight Another Day", pip = 2, faction = Faction.SHADOW_COLLECTIVE, picture = R.drawable.fight_another_day),
        CommandCard(name = "Marked For Elimination", pip = 1, faction = Faction.SHADOW_COLLECTIVE, picture = R.drawable.marked_for_elimination),
        CommandCard(name = "His Eminence", pip = 2, faction = Faction.SHADOW_COLLECTIVE, picture = R.drawable.his_eminence),
        CommandCard(name = "Seize What Power We Can", pip = 3, faction = Faction.SHADOW_COLLECTIVE, picture = R.drawable.seize_what_power_we_can),
        CommandCard(name = "Victory Or Death!", pip = 3, faction = Faction.SHADOW_COLLECTIVE, picture = R.drawable.victory_or_death),
        CommandCard(name = "Witch Magick", pip = 3, faction = Faction.SHADOW_COLLECTIVE, picture = R.drawable.witch_magic)


        // ... add all other cards here
    )

    /**
     * This is the key function. It gets called by the DeckCreationView.
     * It filters the master list to return only the cards for the requested faction.
     * This is very efficient as it only searches the list when needed.
     */
    fun getCardsForFaction(faction: Faction): List<CommandCard> {
        return allCards.filter { it.faction == faction }
    }
}
