package com.example.legioncommander.model.unitcards

import com.example.legioncommander.R
import com.example.legioncommander.model.commandcards.Faction

object UnitCardRepository
{
    private val allCards = listOf(
        // --- Rebel Cards ---
        // --- Empire Cards ---


        // --- Republic Cards ---
        UnitCard(
            id = "obi-wan-kenobi",
            title = "Obi-Wan Kenobi",
            point = 150,
            hp = 6,
            courage = 3,
            speed = 2,
            defensedie = DefenseDie.RED,
            factions = listOf(Faction.REPUBLIC),
            rank = UnitCardRank.COMMANDER,
            type = UnitCardType.TROOPER,
            keywords = mapOf(
                AbilityKeyword.JUMP to 1,
                AbilityKeyword.CHARGE to 0,
                AbilityKeyword.DEFLECT to 0,
                AbilityKeyword.GUARDIAN to 3,
                AbilityKeyword.IMMUNE_PIERCE to 0,
                AbilityKeyword.MASTER_OF_THE_FORCE to 1,
                AbilityKeyword.SORESU_MASTERY to 0
            ),
            imageRes = R.drawable.unit_rep_obi_wan_kenobi
        ),

        // --- CIS Cards ---




        // --- Shadow Collective Cards ---


        // --- Empire and Shadow Collective Cards ---

        UnitCard(
            id = "SC1",
            title = "Black Sun Vigo",
            point = 50,
            hp = 4,
            courage = 2,
            speed = 2,
            defensedie = DefenseDie.RED,
            factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.EMPIRE),
            rank = UnitCardRank.COMMANDER,
            type = UnitCardType.TROOPER,
            keywords = mapOf(
                AbilityKeyword.AID to 0,
                AbilityKeyword.DAUNTLESS to 0,
                AbilityKeyword.INDEPENDENT_AIM to 1
            ),
            imageRes = R.drawable.unit_sc_black_sun_vigo
        ),





    )
}

