package com.example.legioncommander.data

import com.example.legioncommander.R

// A simple object to act as our card database/repository.
object CommandCardRepository {

    // 1. A master list containing ALL cards in the game.
    private val allCards = listOf(
        // --- Generic Cards (faction is null) ---
        //Generic
        CommandCard("gen1", "Ambush", R.drawable.ambush, 1, factions = emptyList()),
        CommandCard("gen2", "Push", R.drawable.push, 2, factions = emptyList()),
        CommandCard("gen3", "Assault", R.drawable.assault, 3, factions = emptyList()),
        CommandCard(
            "gen4",
            "Standing Orders",
            R.drawable.standing_orders,
            4,
            factions = emptyList()
        ),
        //Mercenaries
        CommandCard(
            "gen5",
            "Ploy",
            R.drawable.ploy,
            1,
            factions = emptyList()
        ),

        CommandCard(
            "gen6",
            "Aggression",
            R.drawable.aggression,
            2,
            factions = emptyList()
        ),
        CommandCard(
            "gen7",
            "Discretion",
            R.drawable.discretion,
            3,
            factions = emptyList()
        ),

        // --- Rebels Cards ---
        //Luke
        CommandCard(
            "reb1",
            "Son Of Skywalker",
            R.drawable.son_of_skywalker,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb2",
            "My Ally Is The Force",
            R.drawable.my_ally_is_the_force,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb3",
            "Full Of Surprises",
            R.drawable.full_of_surprises,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb4",
            "Return Of The Jedi",
            R.drawable.return_of_the_jedi,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Lei
        CommandCard(
            "reb5",
            "Coordinated Bombardment",
            R.drawable.coordinated_bombardment,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb6",
            "A Beautiful Friendship",
            R.drawable.a_beautiful_friendship,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb7",
            "No TIme For Sorrows",
            R.drawable.no_time_for_sorrows,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb8",
            "Somebody Has To Save Our Skins",
            R.drawable.somebody_has_to_save_our_skins,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Han Solo
        CommandCard(
            "reb9",
            "Sorry About The Mess",
            R.drawable.sorry_about_the_mess,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb10",
            "Reckless Diversion",
            R.drawable.reckless_diversion,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb11",
            "Change Of Plans",
            R.drawable.change_of_plans,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //TODO: Add more cards for Rebels


        // --- Empire Cards ---


        // --- Republic Cards ---

        // --- CIS Cards ---
        //Generic
        CommandCard(
            "cis8",
            "Mechanized Incursion",
            R.drawable.mechanized_incursion,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Generic
        CommandCard(
            "cis9",
            "Orbital Strike",
            R.drawable.orbital_strike,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Gen. Grevious
        CommandCard(
            "cis1",
            "Trained In Your Jedi Arts",
            R.drawable.trained_in_your_jedi_arts,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis2",
            "Supreme Commander",
            R.drawable.supreme_commander,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis3",
            "Crush Them!",
            R.drawable.crush_them,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Count Dooku
        CommandCard(
            "cis4",
            "Fear, Surprise, and Intimidation",
            R.drawable.fear_surprise_intimidation,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis5",
            "Double The Fall",
            R.drawable.double_the_fall,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis6",
            "The Sith Will Rule",
            R.drawable.the_sith_will_rule,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis7",
            "You Disappoint Me",
            R.drawable.you_disappoint_me,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Kalani
        CommandCard(
            "cis10",
            "They Too Will Suffer",
            R.drawable.they_too_will_suffer,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis11",
            "Preservation Protocols",
            R.drawable.preservation_protocols,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis12",
            "Do Not Underestimate Our Means",
            R.drawable.do_not_underestimate_our_means,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Asajj Ventress
        CommandCard(
            "cis13",
            "The Jedi Shall Fall",
            R.drawable.the_jedi_shall_fall,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis14",
            "I Am Fear",
            R.drawable.i_am_fear,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis15",
            "Yes, My Master",
            R.drawable.yes_my_master,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Sun Fac
        CommandCard(
            "cis16",
            "Brutal Enforcer",
            R.drawable.brutal_enforcer,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Poggle The Lesser
        CommandCard(
            "cis17",
            "Let The Executions Begin!",
            R.drawable.let_the_executions_begin,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis18",
            "We Serve The Queen",
            R.drawable.we_serve_the_queen,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis19",
            "We Serve The Queen",
            R.drawable.we_make_wapons,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),
        //Super Tactical Commander Droid
        CommandCard(
            "cis20",
            "Exterminate",
            R.drawable.exterminate,
            1,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis21",
            "Tactical Download",
            R.drawable.tactical_download,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),





        // --- Shadow Collective Cards ---
        //Darth Maul
        CommandCard(
            "sc1",
            "Witch Magic",
            R.drawable.witch_magic,
            1,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        CommandCard(
            "sc2",
            "His Eminence",
            R.drawable.his_eminence,
            2,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        CommandCard(
            "sc3",
            "Seize What Power We Can",
            R.drawable.seize_what_power_we_can,
            4,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        //Gar Saxon
        CommandCard(
            "sc4",
            "Marked For Elimination",
            R.drawable.marked_for_elimination,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        CommandCard(
            "sc5",
            "Fight Another Day",
            R.drawable.fight_another_day,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        CommandCard(
            "sc6",
            "Victory Or Death",
            R.drawable.victory_or_death,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),


        // --- Shadow Collective & Empire Cards ---


        // --- Shadow Collective & CIS ---
        //Darth Maul
        CommandCard(
            "sc-cis1",
            "Duel Of The Fates",
            R.drawable.duel_of_the_fates,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS)
        ),
        CommandCard(
            "sc-cis2",
            "The Phantom Menace",
            R.drawable.the_phantom_menace,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS)
        ),
        CommandCard(
            "sc-cis3",
            "At Last",
            R.drawable.at_last,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS)
        ),

        // --- Shadow Collective, CIS and Empire ---
        //Bossk
        CommandCard(
            "sc-cis-em1",
            "Merciless Munitions",
            R.drawable.merciless_munitions,
            1,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.EMPIRE)
        ),
        CommandCard(
            "sc-cis-em2",
            "Reptilian Rampage",
            R.drawable.reptilian_rampage,
            2,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.EMPIRE)
        ),
        CommandCard(
            "sc-cis-em3",
            "Lying In Wait",
            R.drawable.lying_in_wait,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.EMPIRE)
        ),
        CommandCard(
            "sc-cis-em4",
            "I'm Your Worst Nightmare",
            R.drawable.im_your_worst_nightmare,
            1,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.EMPIRE)
        ),
        CommandCard(
            "sc-cis-em5",
            "I'm In Control",
            R.drawable.im_in_control,
            2,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.EMPIRE)
        ),
        CommandCard(
            "sc-cis-em6",
            "I Make The Rules Now",
            R.drawable.i_make_the_rules_now,
            3,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.EMPIRE)
        ),


        // ... Add cards for Empire, Republic, etc. here
    )

    /**
     * This is the key function. It will be called by your DeckCreationView.
     * It returns a list containing cards specific to the given faction
     * PLUS all the generic cards.
     */
    fun getCardsForFaction(faction: Faction): List<CommandCard> {
        // 2. Filter the master list.
        return allCards.filter { card ->
            // A card is included if it's generic (faction is null) OR it matches the requested faction.
            card.factions.isEmpty() || card.factions.contains(faction)
        }
    }
}
