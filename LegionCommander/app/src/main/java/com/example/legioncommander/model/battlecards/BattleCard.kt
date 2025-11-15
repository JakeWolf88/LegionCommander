package com.example.legioncommander.model.battlecards

import androidx.annotation.DrawableRes

enum class BattleCardType
{
    PRIMARY,
    SECONDARY,
    ADVANTAGE,
}

data class BattleCard(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val cardType: BattleCardType,
)