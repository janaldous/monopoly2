package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.core.card.ChanceCard;
import com.janaldous.monopoly.core.card.CommunityChestCard;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OriginalCardFactory {

    private PlayerActionFactory playerActionFactory;

    public OriginalCardFactory(PlayerActionFactory playerActionFactory) {
        this.playerActionFactory = playerActionFactory;
    }

    public ChanceCard createChanceCard(String name) {
        switch (name) {
            case "AdvanceToStCharlesPl":
                return new ChanceCard("AdvanceToStCharlesPl", playerActionFactory.createMovePlayerAction("St Charles Place"));
            case "AdvanceToIllinoisAve":
                return new ChanceCard("AdvanceToIllinoisAve", playerActionFactory.createMovePlayerAction("Illinois Ave"));
            case "AdvanceToNearestUtilityThenCanBuy":
                return new ChanceCard("AdvanceToNearestUtilityThenCanBuy", playerActionFactory.createMoveNearestUtilityPlayerAction());
            case "AdvanceToGo":
                return new ChanceCard("AdvanceToGo", playerActionFactory.createMovePlayerAction("Go"));
            case "AdvanceToNearestRailroadThenCanBuy":
                return new ChanceCard("AdvanceToNearestRailroadThenCanBuy", playerActionFactory.createMoveNearestRailroad());
            case "BankDividend":
                return new ChanceCard("BankDividend", playerActionFactory.createPayPlayer(50));
            case "GetOutOfJailFree":
                return new ChanceCard("GetOutOfJailFree", playerActionFactory.createGetOutOfJailFree());
            case "GoBack3Spaces":
                return new ChanceCard("GoBack3Spaces", playerActionFactory.createMoveByStepsPlayerAction(-3));
            case "GoToJail":
                return new ChanceCard("GoToJail", playerActionFactory.createGoToJailAction());
            case "GeneralRepairs":
                return new ChanceCard("GeneralRepairs", playerActionFactory.createMakeGeneralRepairsAction());
            case "PoorTax":
                return new ChanceCard("PoorTax", playerActionFactory.createChargePlayerAction(15));
            case "AdvanceToReadingRailroad":
                return new ChanceCard("AdvanceToReadingRailroad", playerActionFactory.createMovePlayerAction("Reading Railroad"));
            case "AdvanceToBoardwalk":
                return new ChanceCard("AdvanceToBoardwalk", playerActionFactory.createMovePlayerAction("Boardwalk"));
            case "ChairmanOfTheBoard":
                return new ChanceCard("ChairmanOfTheBoard", playerActionFactory.createPayAllPlayersAction(50));
            case "BuildingAndLoadMatures":
                return new ChanceCard("BuildingAndLoadMatures", playerActionFactory.createPayPlayer(150));
            case "CrosswordCompetitionWinner":
                return new ChanceCard("CrosswordCompetitionWinner", playerActionFactory.createPayPlayer(100));
            default:
                throw new IllegalArgumentException("could not recognize " + name);
        }

    }

    public CommunityChestCard createCommunityChestCard(String name) {
        switch(name) {
            case "AdvanceToGo":
                return new CommunityChestCard("AdvanceToGo", playerActionFactory.createMovePlayerAction("Go"));
            default:
                throw new IllegalArgumentException("cannot find card named: " + name);
        }
    }

    public Queue<ChanceCard> createChanceCardDeck() {
        return new LinkedList<>(Arrays.<ChanceCard>asList(
                createChanceCard("AdvanceToGo"),
                createChanceCard("AdvanceToIllinoisAve"),
                createChanceCard("AdvanceToStCharlesPl"),
                createChanceCard("AdvanceToNearestUtilityThenCanBuy"),
                createChanceCard("AdvanceToNearestRailroadThenCanBuy"),
                createChanceCard("GetOutOfJailFree"),
                createChanceCard("GoBack3Spaces"),
                createChanceCard("GoToJail"),
                createChanceCard("PoorTax"),
                createChanceCard("AdvanceToReadingRailroad"),
                createChanceCard("AdvanceToBoardwalk"),
                createChanceCard("ChairmanOfTheBoard"),
                createChanceCard("BuildingAndLoadMatures"),
                createChanceCard("CrosswordCompetitionWinner")
        ));
    }
}
