package com.janaldous.monopoly;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.card.CardFactory;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.*;
import com.janaldous.monopoly.core.space.rentstrategy.NormalResidentialRentStrategy;
import com.janaldous.monopoly.core.space.rentstrategy.ResidentialPropertyGroupRentStrategy;
import com.janaldous.monopoly.core.space.rentstrategy.UtilitySetGroupRentStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test class GameEngineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)378
 */
public class GameEngineTest
{
    Player[] players;
    Gameboard gameboard;
    Token[] tokens;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        
    }

    /**
     * Tears down the test fixture.
     *GameEngineTest.tesBuyPropertyAndHouse
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void tesIncomeTaxSpace() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            spaceFactory.createSpace("IncomeTax")
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList());
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        requiredActions.get(0).act(currentPlayer);
        
        // then
        assertEquals(1300, currentPlayer.getBalance());
        assertEquals(1500, players[1].getBalance());
    }
    
    @Test
    public void tesCommunityChest() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            spaceFactory.createSpace("CommunityChest"),
            createStCharlesPlace(spaceFactory)
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        // pick card
        Optional<PlayerAction> nextAction = requiredActions.get(0).act(currentPlayer);
        nextAction.get().act(currentPlayer);
        
        // then
        assertEquals(2, gameboard.getPosition(tokens[curPlayerIndex]));
    }
    
    @Test
    public void tesBuyProperty() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
                
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            createStCharlesPlace(spaceFactory)
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        Map<String, PlayerAction> playerOptions = space.getPlayerOptions(currentPlayer);
        // buy property
        playerOptions.get("Buy Property").act(currentPlayer);
        
        // then
        assertEquals(currentPlayer, ((PropertySpace) gameboard.getSpaceBySpaceName("St Charles Place")).getOwner());
    }
    
    @Test
    public void tesBuyPropertyAndHouse() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            createStCharlesPlace(spaceFactory)
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        Map<String, PlayerAction> playerOptions = space.getPlayerOptions(currentPlayer);
        // buy property
        assertEquals(1, playerOptions.entrySet().size());
        playerOptions.get("Buy Property").act(currentPlayer);
        
        playerOptions = space.getPlayerOptions(currentPlayer);
        assertEquals(2, playerOptions.entrySet().size());
        playerOptions.get("Buy House").act(currentPlayer);
        
        // then
        assertEquals(currentPlayer, ((PropertySpace) gameboard.getSpaceBySpaceName("St Charles Place")).getOwner());
        assertEquals(1, ((ResidentialSpace) gameboard.getSpaceBySpaceName("St Charles Place")).getHouseQty());
    }
    
    
    
    @Test
    public void tesCollectRent() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        ResidentialSpace property = (ResidentialSpace) createStCharlesPlace(spaceFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            property
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        property.setOwner(players[0]);
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 1;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        // pay rent
        requiredActions.get(0).act(currentPlayer);
        
        // then
        assertEquals(1510, players[0].getBalance());
        assertEquals(1490, currentPlayer.getBalance());
        
    }
    
    @Test
    public void tesCollectColorSetRent() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
                
        ResidentialSpace stCharlesPlace = (ResidentialSpace) createStCharlesPlace(spaceFactory);
        stCharlesPlace.setStrategy(new ResidentialPropertyGroupRentStrategy(stCharlesPlace));
        ResidentialSpace statesAve = (ResidentialSpace) createStatesAve(spaceFactory);
        statesAve.setStrategy(new ResidentialPropertyGroupRentStrategy(statesAve));
        ResidentialSpace virginiaAve = (ResidentialSpace) createVirginiaAve(spaceFactory);
        virginiaAve.setStrategy(new ResidentialPropertyGroupRentStrategy(virginiaAve));
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            stCharlesPlace,
            statesAve,
            virginiaAve
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        stCharlesPlace.setOwner(players[0]);
        statesAve.setOwner(players[0]);
        virginiaAve.setOwner(players[0]);
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 1;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 3);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        // pay rent
        requiredActions.get(0).act(currentPlayer);
        
        // then
        assertEquals(1524, players[0].getBalance());
        assertEquals(1476, currentPlayer.getBalance());
    }
    
    @Test
    public void tesBuyAllColorSetThenCheckRentStrategy() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
                
        ResidentialSpace stCharlesPlace = (ResidentialSpace) createStCharlesPlace(spaceFactory);
        ResidentialSpace statesAve = (ResidentialSpace) createStatesAve(spaceFactory);
        ResidentialSpace virginiaAve = (ResidentialSpace) createVirginiaAve(spaceFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            stCharlesPlace,
            statesAve,
            virginiaAve
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        Player player0 = players[0];
        Space space1 = gameboard.move(tokens[0], 1);
        // buy
        space1.getPlayerOptions(player0).get("Buy Property").act(player0);
        
        Space space2 = gameboard.move(tokens[0], 1);
        // buy
        space2.getPlayerOptions(player0).get("Buy Property").act(player0);
        
        Space space3 = gameboard.move(tokens[0], 1);
        // buy
        space3.getPlayerOptions(player0).get("Buy Property").act(player0);
        
        // then
        assertEquals(3, player0.getProperties().get(ColorGroup.PINK).size());
        assertTrue(((PropertySpace) space1).getStrategy() instanceof ResidentialPropertyGroupRentStrategy);
        assertTrue(((PropertySpace) space2).getStrategy() instanceof ResidentialPropertyGroupRentStrategy);
        assertTrue(((PropertySpace) space3).getStrategy() instanceof ResidentialPropertyGroupRentStrategy);
    }

    @Test
    public void givenPlayerHasAllPropertyGroup_whenSellOneProperty_thenRentStrategyRevertsToNormal() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
                
        ResidentialSpace stCharlesPlace = (ResidentialSpace) createStCharlesPlace(spaceFactory);
        ResidentialSpace statesAve = (ResidentialSpace) createStatesAve(spaceFactory);
        ResidentialSpace virginiaAve = (ResidentialSpace) createVirginiaAve(spaceFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            stCharlesPlace,
            statesAve,
            virginiaAve
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players

        Player player0 = new Player("A", 1500);
        players = new Player[] {player0, new Player("B", 1500)};
        player0.addProperty(stCharlesPlace);
        stCharlesPlace.setOwner(player0);
        stCharlesPlace.setStrategy(new ResidentialPropertyGroupRentStrategy(stCharlesPlace));
        player0.addProperty(statesAve);
        statesAve.setStrategy(new ResidentialPropertyGroupRentStrategy(statesAve));
        statesAve.setOwner(player0);
        player0.addProperty(virginiaAve);
        virginiaAve.setStrategy(new ResidentialPropertyGroupRentStrategy(virginiaAve));
        virginiaAve.setOwner(player0);
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        Space space1 = gameboard.move(tokens[0], 1);
        // sell
        PlayerAction sellAction = space1.getPlayerOptions(player0).get("Sell Property");
        sellAction.act(player0);
        
        // then
        assertEquals(2, player0.getProperties().get(ColorGroup.PINK).size());
        gameboard.getProperties().get(ColorGroup.PINK).forEach(p -> assertTrue(p.getStrategy() instanceof NormalResidentialRentStrategy));
    }
    
    @Test
    public void testBuyUtilityGroup_thenExpectDifferentRent() throws PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
                
        UtilityCompanySpace electricCompany = spaceFactory.createUtility("Electric Company", 150);
        UtilityCompanySpace waterCompany = spaceFactory.createUtility("Water Company", 150);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            electricCompany,
            waterCompany
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        dice.setStrategy(() -> 11);
        // pick order of players

        Player player0 = new Player("A", 1500);
        Player playerB = new Player("B", 1500);
        players = new Player[] {player0, playerB};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        // buy
        Space space1 = gameboard.move(tokens[0], 1);
        space1.getPlayerOptions(player0).get("Buy Property").act(player0);
        
        // player 2 pays rent - rolls 11 then 4 x 11 = $44
        dice.roll();
        Space spaceA1 = gameboard.move(tokens[1], 1);
        spaceA1.getRequiredActions().get(0).act(playerB);
        
        assertEquals(1500-150+44, player0.getBalance());
        assertEquals(1500-44, playerB.getBalance());
        
        // buy
        Space space2 = gameboard.move(tokens[0], 1);
        space2.getPlayerOptions(player0).get("Buy Property").act(player0);
        
        // pay rent - rolls 11 then 10 x 11 = $110
        dice.roll();
        Space spaceA2 = gameboard.move(tokens[1], 1);
        spaceA2.getRequiredActions().get(0).act(playerB);
        
        assertEquals(1500-150+44-150+110, player0.getBalance());
        assertEquals(1500-44-110, playerB.getBalance());
        
        // then
        assertEquals(2, player0.getProperties().get(ColorGroup.UTILITY).size());
        gameboard.getProperties().get(ColorGroup.UTILITY).forEach(p -> assertTrue(p.getStrategy() instanceof UtilitySetGroupRentStrategy));
    }
    
    private ResidentialSpace createStCharlesPlace(SpaceFactory spaceFactory) {
        ResidentialSpace rs = (ResidentialSpace) spaceFactory.createPropertySpace("residential", "St Charles Place", 140, 100, 100, 10, 50, 750, ColorGroup.PINK);
        rs.setStrategy(new NormalResidentialRentStrategy(rs));
        return rs;
    }
    
    private ResidentialSpace createStatesAve(SpaceFactory spaceFactory) {
        ResidentialSpace rs = (ResidentialSpace) spaceFactory.createPropertySpace("residential", "States Ave", 140, 100, 100, 10, 50, 750, ColorGroup.PINK);
        rs.setStrategy(new NormalResidentialRentStrategy(rs));
        return rs;
    }
    
    private ResidentialSpace createVirginiaAve(SpaceFactory spaceFactory) {
        ResidentialSpace rs = (ResidentialSpace) spaceFactory.createPropertySpace("residential", "Virginia Ave", 140, 100, 100, 12, 60, 900, ColorGroup.PINK);
        rs.setStrategy(new NormalResidentialRentStrategy(rs));
        return rs;
    }
    
    private void actAllActions(Map<String, PlayerAction> playerActions, Player player) throws PlayerActionException {
        for (PlayerAction pa: playerActions.values()) {
            pa.act(player);
        }
    }
}
