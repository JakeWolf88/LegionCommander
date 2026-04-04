package com.example.legioncommander.model.unitcards

import androidx.annotation.DrawableRes
import com.example.legioncommander.model.commandcards.Faction

enum class UnitCardType
{
    TROOPER,
    REPULSOR_VEHICLE,
}

enum class DefenseDie
{
    WHITE,
    BLACK,
    RED,
}

enum class UnitCardRank
{
    COMMANDER,
    OPERATIVE,
    CORPS,
    SPECIAL_FORCES,
    SUPPORT,
    HEAVY,
}

enum class SurgeChart
{
    BLANK,
    HIT,
    CRIT,
}

enum class AbilityKeyword {

    AID,
    CHARGE,
    DAUNTLESS,
    DEFLECT,
    GUARDIAN,
    IMMUNE_PIERCE,
    INSPIRE,
    JUMP,
    SURGE_CRITICAL,
    IMPACT,
    INDEPENDENT_AIM,
    MASTER_OF_THE_FORCE,
    PIERCE,
    SORESU_MASTERY,
    // Add others as needed
}

data class UnitCard(
    val id: String,
    val title: String,
    val point: Int,
    val hp: Int,
    val courage: Int,
    val speed: Int,
    val defensedie: DefenseDie,
    val miniatureNumbers: Int = 1,
    val surgeChartAttack: SurgeChart = SurgeChart.BLANK,
    val surgeChartDefense: SurgeChart = SurgeChart.BLANK,
    val factions: List<Faction> = emptyList(),
    val rank: UnitCardRank,
    val type: UnitCardType,
    val keywords: Map<AbilityKeyword, Int> = emptyMap(),
    @DrawableRes val imageRes: Int,
)