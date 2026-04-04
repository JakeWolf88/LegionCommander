package com.example.legioncommander.model.battlecards

import androidx.annotation.DrawableRes

enum class BattleCardType
{
    PRIMARY,
    SECONDARY,
    ADVANTAGE,
}

enum class AdvantageCardType
{
    GENERAL,
    REBELS,
    EMPIRE,
    REPUBLIC,
    CIS,
}

data class BattleCard(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val cardType: BattleCardType,
    val cardTypeFaction: AdvantageCardType = AdvantageCardType.GENERAL
)