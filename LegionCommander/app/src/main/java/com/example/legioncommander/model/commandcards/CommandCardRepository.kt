package com.example.legioncommander.model.commandcards

import com.example.legioncommander.R

object CommandCardRepository {

    private val allCards = listOf(
        // --- Generic Cards (faction is null) ---
        //Generic
        CommandCard(
            "gen1",
            "Ambush",
            _root_ide_package_.com.example.legioncommander.R.drawable.ambush,
            1,
            factions = emptyList()),
        CommandCard(
            "gen2",
            "Push",
            _root_ide_package_.com.example.legioncommander.R.drawable.push,
            2,
            factions = emptyList()),
        CommandCard(
            "gen3",
            "Assault",
            _root_ide_package_.com.example.legioncommander.R.drawable.assault,
            3,
            factions = emptyList()),
        CommandCard(
            "gen4",
            "Standing Orders",
            _root_ide_package_.com.example.legioncommander.R.drawable.standing_orders,
            4,
            factions = emptyList()
        ),
        //Mercenaries
        CommandCard(
            "gen5",
            "Ploy",
            _root_ide_package_.com.example.legioncommander.R.drawable.ploy,
            1,
            factions = emptyList()
        ),
        CommandCard(
            "gen6",
            "Aggression",
            _root_ide_package_.com.example.legioncommander.R.drawable.aggression,
            2,
            factions = emptyList()
        ),
        CommandCard(
            "gen7",
            "Discretion",
            _root_ide_package_.com.example.legioncommander.R.drawable.discretion,
            3,
            factions = emptyList()
        ),

        // --- Rebels Cards ---
        //General
        CommandCard(
            "rebgen1",
            "Sabotaged Communications",
            _root_ide_package_.com.example.legioncommander.R.drawable.sabotaged_communications,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "rebgen2",
            "Turning The Tide",
            _root_ide_package_.com.example.legioncommander.R.drawable.turning_the_tide,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "rebgen3",
            "Covering Fire",
            _root_ide_package_.com.example.legioncommander.R.drawable.covering_fire,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Luke
        CommandCard(
            "reb1",
            "Son Of Skywalker",
            _root_ide_package_.com.example.legioncommander.R.drawable.son_of_skywalker,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb2",
            "My Ally Is The Force",
            _root_ide_package_.com.example.legioncommander.R.drawable.my_ally_is_the_force,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb3",
            "Full Of Surprises",
            _root_ide_package_.com.example.legioncommander.R.drawable.full_of_surprises,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb4",
            "Return Of The Jedi",
            _root_ide_package_.com.example.legioncommander.R.drawable.return_of_the_jedi,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Leia
        CommandCard(
            "reb5",
            "Coordinated Bombardment",
            _root_ide_package_.com.example.legioncommander.R.drawable.coordinated_bombardment,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb6",
            "A Beautiful Friendship",
            _root_ide_package_.com.example.legioncommander.R.drawable.a_beautiful_friendship,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb7",
            "No TIme For Sorrows",
            _root_ide_package_.com.example.legioncommander.R.drawable.no_time_for_sorrows,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb8",
            "Somebody Has To Save Our Skins",
            _root_ide_package_.com.example.legioncommander.R.drawable.somebody_has_to_save_our_skins,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Han Solo
        CommandCard(
            "reb9",
            "Sorry About The Mess",
            _root_ide_package_.com.example.legioncommander.R.drawable.sorry_about_the_mess,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb10",
            "Reckless Diversion",
            _root_ide_package_.com.example.legioncommander.R.drawable.reckless_diversion,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb11",
            "Change Of Plans",
            _root_ide_package_.com.example.legioncommander.R.drawable.change_of_plans,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Chewbacca
        CommandCard(
            "reb12",
            "Common Cause",
            _root_ide_package_.com.example.legioncommander.R.drawable.common_cause,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb13",
            "Brains and Brawn",
            _root_ide_package_.com.example.legioncommander.R.drawable.brains_and_brawn,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb14",
            "Notorious Scoundrels",
            _root_ide_package_.com.example.legioncommander.R.drawable.notorious_scoundrels,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Sabine
        CommandCard(
            "reb15",
            "Explosions!",
            _root_ide_package_.com.example.legioncommander.R.drawable.explosions,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb16",
            "Symbol Of Rebellion",
            _root_ide_package_.com.example.legioncommander.R.drawable.symbol_of_rebellion,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb17",
            "Legacy Of Mandalore",
            _root_ide_package_.com.example.legioncommander.R.drawable.legacy_of_mandalore,
            3,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb18",
            "Crack Shot",
            _root_ide_package_.com.example.legioncommander.R.drawable.crack_shot,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb19",
            "Last Stand",
            _root_ide_package_.com.example.legioncommander.R.drawable.last_stand,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb20",
            "Volunteer Mission",
            _root_ide_package_.com.example.legioncommander.R.drawable.volunteer_mission,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //K-2SO
        CommandCard(
            "reb21",
            "Sacrifice",
            _root_ide_package_.com.example.legioncommander.R.drawable.sacrifice,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Lando
        CommandCard(
            "reb22",
            "Corellian Spike",
            _root_ide_package_.com.example.legioncommander.R.drawable.corellian_spike,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb22",
            "Ace Up His Sleeve",
            _root_ide_package_.com.example.legioncommander.R.drawable.ace_up_his_sleeve,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb23",
            "Idiots Array",
            _root_ide_package_.com.example.legioncommander.R.drawable.idiots_array,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Rebel Agent
        CommandCard(
            "reb27",
            "BattleField Reconnaissance",
            _root_ide_package_.com.example.legioncommander.R.drawable.battlefield_reconnaissance,
            2,
            factions = listOf(Faction.REBELS)
        ),
        //Rebel Officer
        CommandCard(
            "reb28",
            "Diversionary Tactics",
            _root_ide_package_.com.example.legioncommander.R.drawable.diversionary_tactics,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Jyn Erso
        CommandCard(
            "reb29",
            "I Rebel",
            _root_ide_package_.com.example.legioncommander.R.drawable.i_rebel,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb30",
            "Trust Goes Both Ways",
            _root_ide_package_.com.example.legioncommander.R.drawable.trust_goes_both_ways,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb31",
            "Rebellions Are Built On Hope",
            _root_ide_package_.com.example.legioncommander.R.drawable.rebellions_are_built_on_hope,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Grogu
        CommandCard(
            "reb32",
            "The Hand Thing",
            _root_ide_package_.com.example.legioncommander.R.drawable.the_hand_thing,
            2,
            factions = listOf(Faction.REBELS)
        ),
        //Wicket
        CommandCard(
            "reb33",
            "Close Call",
            _root_ide_package_.com.example.legioncommander.R.drawable.close_call,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb34",
            "Sneaky Scouting",
            _root_ide_package_.com.example.legioncommander.R.drawable.sneacky_scouting,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb35",
            "Courageous Counterattack",
            _root_ide_package_.com.example.legioncommander.R.drawable.courageous_counterattack,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //Ahsoka
        CommandCard(
            "reb36",
            "I Am No Jedi",
            _root_ide_package_.com.example.legioncommander.R.drawable.i_am_no_jedi,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb37",
            "Family Reunion",
            _root_ide_package_.com.example.legioncommander.R.drawable.family_reunion,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb38",
            "Swift Protector",
            _root_ide_package_.com.example.legioncommander.R.drawable.swift_protector,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb39",
            "A New Beginning",
            _root_ide_package_.com.example.legioncommander.R.drawable.a_new_beginning,
            3,
            factions = listOf(Faction.REBELS)
        ),

        // --- Empire Cards ---
        //General
        CommandCard(
            "em1",
            "Convert Observation",
            _root_ide_package_.com.example.legioncommander.R.drawable.covert_observation,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em2",
            "Pinned Down",
            _root_ide_package_.com.example.legioncommander.R.drawable.pinned_down,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em3",
            "Coordinated Fire",
            _root_ide_package_.com.example.legioncommander.R.drawable.coordinated_fire,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //Darth Vader
        CommandCard(
            "em4",
            "Implacable",
            _root_ide_package_.com.example.legioncommander.R.drawable.implacable,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em5",
            "Vader's Might",
            _root_ide_package_.com.example.legioncommander.R.drawable.vaders_might,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em6",
            "New Ways To Motivate Them",
            _root_ide_package_.com.example.legioncommander.R.drawable.new_ways_to_motivate_them,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em7",
            "Fear And Dead Men",
            _root_ide_package_.com.example.legioncommander.R.drawable.fear_and_dead_men,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em8",
            "Master Of Evil",
            _root_ide_package_.com.example.legioncommander.R.drawable.master_of_evil,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em9",
            "Darkness Descends",
            _root_ide_package_.com.example.legioncommander.R.drawable.darkness_descends,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //General Veers
        CommandCard(
            "em10",
            "Maximum Firepower",
            _root_ide_package_.com.example.legioncommander.R.drawable.maximum_firepower,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em11",
            "Evasive Maneuvers",
            _root_ide_package_.com.example.legioncommander.R.drawable.evasive_maneuvers,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em12",
            "Imperial Discipline",
            _root_ide_package_.com.example.legioncommander.R.drawable.imperial_discipline,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //Director Orson Krennic
        CommandCard(
            "em13",
            "Voracious Ambition",
            _root_ide_package_.com.example.legioncommander.R.drawable.voracious_ambitions,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em14",
            "Deploy The Garrison",
            _root_ide_package_.com.example.legioncommander.R.drawable.deploy_the_garrison,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em15",
            "Annihilation Looms",
            _root_ide_package_.com.example.legioncommander.R.drawable.annihilation_looms,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //Iden Versio
        CommandCard(
            "em16",
            "Pulse Scan",
            _root_ide_package_.com.example.legioncommander.R.drawable.pulse_scan,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em17",
            "Incapacitate",
            _root_ide_package_.com.example.legioncommander.R.drawable.incapacitate,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em18",
            "Concussive Blast",
            _root_ide_package_.com.example.legioncommander.R.drawable.concussive_blast,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em19",
            "Tactical Strike",
            _root_ide_package_.com.example.legioncommander.R.drawable.tactical_strike,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //Agent Kallus
        CommandCard(
            "em20",
            "Face Me!",
            _root_ide_package_.com.example.legioncommander.R.drawable.face_me,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em21",
            "ISB Investigation",
            _root_ide_package_.com.example.legioncommander.R.drawable.isb_investigation,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em22",
            "Ruthless Tactics",
            _root_ide_package_.com.example.legioncommander.R.drawable.ruthless_tactics,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em23",
            "Ruthless Tactics",
            _root_ide_package_.com.example.legioncommander.R.drawable.ruthless_tactics,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //IG-88
        CommandCard(
            "em24",
            "Focused On The Kill",
            _root_ide_package_.com.example.legioncommander.R.drawable.focused_on_the_kill,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em25",
            "Independent Programming",
            _root_ide_package_.com.example.legioncommander.R.drawable.independent_programming,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //Moff Gideon
        CommandCard(
            "em26",
            "Die At My Hand",
            _root_ide_package_.com.example.legioncommander.R.drawable.die_at_my_hand,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em27",
            "You Have Something I Want",
            _root_ide_package_.com.example.legioncommander.R.drawable.you_have_something_i_want,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em28",
            "Moment Of Consideration",
            _root_ide_package_.com.example.legioncommander.R.drawable.moment_of_consideration,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        //Fifth Brother
        CommandCard(
            "em29",
            "You Would Question Me?",
            _root_ide_package_.com.example.legioncommander.R.drawable.you_would_question_me,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em30",
            "I Care Not For Your Struggles",
            R.drawable.i_care_not_for_your_struggles,
            3,
            factions = listOf(Faction.EMPIRE)
        ),
        //Seventh Sister
        CommandCard(
            "em30",
            "Come And Prove It",
            R.drawable.come_and_prove_it,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em31",
            "UnExpected, But Not Unwelcome",
            R.drawable.unexpected_but_not_unwelcome,
            2,
            factions = listOf(Faction.EMPIRE)
        ),
        CommandCard(
            "em32",
            "You Hide Your Fear Well",
            R.drawable.you_hide_your_fear_well,
            3,
            factions = listOf(Faction.EMPIRE)
        ),
        //Imperial Agent
        CommandCard(
            "em33",
            "Fearsome Oration",
            R.drawable.fearsome_oration,
            1,
            factions = listOf(Faction.EMPIRE)
        ),
        //Imperial Officer
        CommandCard(
            "em34",
            "Inexorable Advance",
            R.drawable.inexorable_advance,
            3,
            factions = listOf(Faction.EMPIRE)
        ),

        // --- Republic Cards ---
        //General
        CommandCard(
            "rep1",
            "Synchronized Offensive",
            R.drawable.synchronized_offensive,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep2",
            "Defiance",
            R.drawable.defiance,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep3",
            "Get In Position",
            R.drawable.get_in_position,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep4",
            "Air Support",
            R.drawable.air_support,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep5",
            "Attack Of The Clones",
            R.drawable.attack_of_the_clones,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep6",
            "Daring Defense",
            R.drawable.daring_defense,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Chewbacca
        CommandCard(
            "rep7",
            "Size Matters Not",
            R.drawable.size_matters_not,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Obi-Wan
        CommandCard(
            "rep8",
            "Hello There",
            R.drawable.hello_there,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep9",
            "Knowledge And Defense",
            R.drawable.knowledge_and_defense,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep10",
            "General Kenobi",
            R.drawable.general_kenobi,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Clone Captain Rex
        CommandCard(
            "rep11",
            "Call Me Captain",
            R.drawable.call_me_captain,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep12",
            "Take That Clankers",
            R.drawable.take_that_clankers,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep13",
            "We're Not Programmed",
            R.drawable.were_not_programmed,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Padme Amidala
        CommandCard(
            "rep14",
            "Our Fate Is In Your Hands",
            R.drawable.our_fate_is_in_your_hands,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep15",
            "Aggressive Negotiations",
            R.drawable.aggressive_negotiations,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep16",
            "Diplomatic Cover",
            R.drawable.diplomatic_cover,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Anakin Skywalker
        CommandCard(
            "rep17",
            "This Is Where The Fun Beings Begin",
            R.drawable.this_is_where_the_fun_beings,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep18",
            "You Underestimate My Power",
            R.drawable.you_underestimate_my_power,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep19",
            "General Skywalker",
            R.drawable.general_skywalker,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Yoda
        CommandCard(
            "rep20",
            "Size Matters Not",
            R.drawable.size_matters_not,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep21",
            "There Is No Try",
            R.drawable.there_is_no_try,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep22",
            "Luminous Beings Are We",
            R.drawable.luminous_beings_are_we,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Clone Commander Cody
        CommandCard(
            "rep23",
            "Bring It Down",
            R.drawable.bring_it_down,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep24",
            "Have I Ever Let You Down",
            R.drawable.have_i_ever_let_you_down,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep25",
            "Combined Arms",
            R.drawable.combined_arms,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep26",
            "Defiance",
            R.drawable.defiance,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Ahsoka Tano
        CommandCard(
            "rep27",
            "You Are Beaten",
            R.drawable.you_are_beaten,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep28",
            "Revenge Is Not The Jedi Way",
            R.drawable.revenge_is_not_the_jedi_way,
            2,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep29",
            "I Go Where I'm Needed",
            R.drawable.i_go_where_im_needed,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),
        //Jedi Knight General
        CommandCard(
            "rep30",
            "Flow Of The Force",
            R.drawable.flow_of_the_force,
            1,
            factions = listOf(Faction.REPUBLIC)
        ),
        CommandCard(
            "rep31",
            "Affirmative General",
            R.drawable.affirmative_general,
            3,
            factions = listOf(Faction.REPUBLIC)
        ),

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
        //General Grevious On Bike
        CommandCard(
            "cis22",
            "Full Throttle",
            R.drawable.full_throttle,
            3,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis23",
            "Pincer Attack",
            R.drawable.pincer_attack,
            2,
            factions = listOf(Faction.SEPARATISTS)
        ),
        CommandCard(
            "cis24",
            "I'll Do It Myself",
            R.drawable.ill_do_it_myself,
            1,
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

        //Empire and Rebels
        //Bobba Fett
        CommandCard(
            "reb-em1",
            "Whipcord Launcher",
            R.drawable.whipcord_launcher,
            1,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        CommandCard(
            "reb-em2",
            "Z-6 Jetpack Rocket",
            R.drawable.z6_jetpack_rocket,
            3,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        CommandCard(
            "reb-em3",
            "A Simply Man",
            R.drawable.a_simple_man,
            1,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb-em4",
            "Making His Way In The Galaxy",
            R.drawable.making_his_way_in_the_galaxy,
            2,
            factions = listOf(Faction.REBELS)
        ),
        CommandCard(
            "reb-em5",
            "Rule With Respect",
            R.drawable.rule_with_respect,
            3,
            factions = listOf(Faction.REBELS)
        ),
        //IG-11
        CommandCard(
            "reb-em6",
            "Mechanical Carnage",
            R.drawable.mechanical_carnage,
            1,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        CommandCard(
            "reb-em7",
            "A Machine Made For Killing",
            R.drawable.a_machine_made_for_killing,
            2,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        CommandCard(
            "reb-em8",
            "Anti-Capture Protocols",
            R.drawable.anti_capture_protocols,
            3,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        //Din Jarin
        CommandCard(
            "reb-em9",
            "This Is The Way",
            R.drawable.this_is_the_way,
            1,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        CommandCard(
            "reb-em10",
            "I Like Those Odds",
            R.drawable.i_like_those_odds,
            2,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),
        CommandCard(
            "reb-em11",
            "Whistling Birds",
            R.drawable.whistling_birds,
            3,
            factions = listOf(Faction.REBELS, Faction.EMPIRE)
        ),

        //Rebels and Republic
        //R2D2
        CommandCard(
            "rep-reb1",
            "Blast Off",
            R.drawable.blast_off,
            1,
            factions = listOf(Faction.REBELS, Faction.REPUBLIC)
        ),
        CommandCard(
            "rep-reb2",
            "Impromptu Immolation",
            R.drawable.impromptu_immolation,
            2,
            factions = listOf(Faction.REBELS, Faction.REPUBLIC)
        ),
        CommandCard(
            "rep-reb3",
            "Smoke Screen",
            R.drawable.smoke_screen,
            3,
            factions = listOf(Faction.REBELS, Faction.REPUBLIC)
        ),
        //The Bad Batch
        CommandCard(
            "rep-reb4",
            "We Do What We Do",
            R.drawable.we_do_what_we_do,
            3,
            factions = listOf(Faction.REBELS, Faction.REPUBLIC)
        ),

        //Empire, Rebels, Republic, CIS
        CommandCard(
            "em-reb-rep-cis1",
            "That's Just Good Business",
            R.drawable.thats_just_good_business,
            2,
            factions = listOf(Faction.EMPIRE, Faction.REBELS, Faction.REPUBLIC, Faction.SEPARATISTS)
        ),
        CommandCard(
            "em-reb-rep-cis2",
            "Stories So Many Of Them True",
            R.drawable.stories_so_many_of_them_true,
            2,
            factions = listOf(Faction.EMPIRE, Faction.REBELS, Faction.REPUBLIC, Faction.SEPARATISTS)
        ),
    )

    fun getCardsForFaction(faction: Faction): List<CommandCard> {
        return allCards.filter { card ->
            card.factions.isEmpty() || card.factions.contains(faction)
        }
    }

    fun getAllCards(): List<CommandCard> {
        return allCards
    }
}