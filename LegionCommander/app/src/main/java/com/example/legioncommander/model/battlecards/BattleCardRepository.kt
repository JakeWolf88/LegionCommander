package com.example.legioncommander.model.battlecards

import com.example.legioncommander.R

object BattleCardRepository {

    //Primaries
    private val allCards = listOf(
        BattleCard(id = "BTP",
                   title = "BreakThrough",
                   R.drawable.breakthrough,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "BAP",
                   title = "Bunker Assault",
                   R.drawable.bunker_assault,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "CTPP",
                   title = "CLose The Pocket",
                   R.drawable.close_the_pocket,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "ITSP",
                   title = "Intercept The Signals",
                   R.drawable.intercept_the_signals,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "RTRP",
                   title = "Recover The Research",
                   R.drawable.recover_the_research,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "SPP",
                   title = "Shifting Priorities",
                   R.drawable.shifting_priorities,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "OP",
                   title = "Outflank",
                   R.drawable.outflank,
                   cardType = BattleCardType.PRIMARY
        ),

        //Secondaries
        BattleCard(id = "BTTHS",
                   title = "Bring Them To Heel",
                   R.drawable.bring_them_to_heel,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "DEBS",
                   title = "Destroy Enemy Base",
                   R.drawable.destroy_enemy_base,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "MTS",
                   title = "Marked Targets",
                   R.drawable.marked_targets,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "RMS",
                   title = "Recon Mission",
                   R.drawable.recon_mission,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "SSS",
                   title = "Surface Scan",
                   R.drawable.surface_scan,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "SACS",
                   title = "Marked Targets",
                   R.drawable.sweep_and_clear,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "SRS",
                   title = "Supply Run",
                   R.drawable.supply_run,
                   cardType = BattleCardType.SECONDARY
        ),

        //Advantages
        BattleCard(id = "AIA",
                   title = "Advanced Intel",
                   R.drawable.advanced_intel,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "CDA",
                   title = "Cunning Deployment",
                   R.drawable.cunning_deployment,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "FPA",
                   title = "Fortified Position",
                   R.drawable.fortified_position,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "GA",
                   title = "Garrison",
                   R.drawable.garrison,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "OA",
                   title = "Ordnance",
                   R.drawable.ordnance,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "SRA",
                   title = "Strafing Run",
                   R.drawable.strafing_run,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "BOA",
                   title = "Black Ops",
                   R.drawable.black_ops,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "COA",
                   title = "Command Override",
                   R.drawable.command_override,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "CSA",
                   title = "Coordinated Strike",
                   R.drawable.coordinated_strike,
                   cardType = BattleCardType.ADVANTAGE
        ),
        BattleCard(id = "NTTLA",
                   title = "No Time To Lose",
                   R.drawable.no_time_to_lose,
                   cardType = BattleCardType.ADVANTAGE
        ),
    )

    fun getCardsForType(cardType: BattleCardType): List<BattleCard> {
        return allCards.filter { card -> card.cardType == cardType }
    }

    fun getReconDeck(): List<BattleCard> {
        return listOf(
            //Primaries
            BattleCard(
                id = "RCTP",
                title = "Close The Pocket",
                R.drawable.recon_close_the_pocket,
                cardType = BattleCardType.PRIMARY
            ),
            BattleCard(
                id = "RITS",
                title = "Intercept The Signals",
                R.drawable.recon_intercept_the_signals,
                cardType = BattleCardType.PRIMARY
            ),
            BattleCard(
                id = "RBA",
                title = "Bunker Assault",
                R.drawable.recon_bunker_assault,
                cardType = BattleCardType.PRIMARY
            ),

            //Secondary
            BattleCard(
                id = "RBTH",
                title = "Bring Them To Heel",
                R.drawable.recon_bring_them_to_heel,
                cardType = BattleCardType.SECONDARY
            ),
            BattleCard(
                id = "RRM",
                title = "Recon Mission",
                R.drawable.recon_recon_mission,
                cardType = BattleCardType.SECONDARY
            ),
            BattleCard(
                id = "RSC",
                title = "Surface Scan",
                R.drawable.recon_surface_scan,
                cardType = BattleCardType.SECONDARY
            ),

            //Advantages
            BattleCard(
                id = "RAI",
                title = "Advanced Intel",
                R.drawable.advanced_intel,
                cardType = BattleCardType.ADVANTAGE
            ),
            BattleCard(
                id = "RCD",
                title = "Cunning Deployment",
                R.drawable.cunning_deployment,
                cardType = BattleCardType.ADVANTAGE
            ),
            BattleCard(
                id = "RFP",
                title = "Fortified Position",
                R.drawable.fortified_position,
                cardType = BattleCardType.ADVANTAGE
            ),
        )
    }

    fun getAllCards(): List<BattleCard> {
        return allCards
    }
}