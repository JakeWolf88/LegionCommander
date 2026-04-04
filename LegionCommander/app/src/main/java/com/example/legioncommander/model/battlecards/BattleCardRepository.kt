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
                   title = "Close The Pocket",
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
        BattleCard(id = "CP",
                   title = "Cauldron",
                   R.drawable.cauldron,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "CCP",
                   title = "Contact, Contact!",
                   R.drawable.contact_contact,
                   cardType = BattleCardType.PRIMARY
        ),
        BattleCard(id = "PP",
                   title = "Payload",
                   R.drawable.payload,
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
        BattleCard(id = "ATRS",
                   title = "Align the Relay",
                   R.drawable.align_the_relay,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "FNS",
                   title = "Failed Negotiations",
                   R.drawable.failed_negotiations,
                   cardType = BattleCardType.SECONDARY
        ),
        BattleCard(id = "RTDS",
                   title = "Retrieve the Data",
                   R.drawable.retrieve_the_data,
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
        BattleCard(id = "SOA",
                   title = "Scrambled Orders",
                   R.drawable.scrambled_orders,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.REBELS
        ),
        BattleCard(id = "NTTLA",
                   title = "No Time To Lose",
                   R.drawable.no_time_to_lose,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.REBELS
        ),
        BattleCard(id = "BOA",
                   title = "Black Ops",
                   R.drawable.black_ops,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.EMPIRE
        ),
        BattleCard(id = "EDA",
                   title = "Extreme Discipline",
                   R.drawable.extreme_discipline,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.EMPIRE
        ),
        BattleCard(id = "CSA",
                   title = "Coordinated Strike",
                   R.drawable.coordinated_strike,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.REPUBLIC
        ),
        BattleCard(id = "RDA",
                   title = "Rapid Deployment",
                   R.drawable.rapid_deployment,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.REPUBLIC
        ),
        BattleCard(id = "COA",
                   title = "Command Override",
                   R.drawable.command_override,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.CIS
        ),
        BattleCard(id = "AAA",
                   title = "Armored Assault",
                   R.drawable.armored_assault,
                   cardType = BattleCardType.ADVANTAGE,
                   cardTypeFaction = AdvantageCardType.CIS
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