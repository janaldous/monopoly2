package com.janaldous.monopoly;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.dice.DiceImpl;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gameboard.GameboardImpl;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.core.token.CarToken;
import com.janaldous.monopoly.core.token.DogToken;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.game.GameConfig;

import java.util.*;
import java.util.stream.Collectors;

public class GameImpl implements Game {

    private final GameConfig config;
    private final List<Player> players;
    private final Gameboard gameboard;
    private final Bank bank;

    public GameImpl(GameConfig config, Gameboard gameboard, Bank bank) {
        this.config = config;

        this.players = createPlayers(config.getPlayerNames(), config.initialMoney());
        this.gameboard = gameboard;
        this.bank = bank;
    }

    private List<Player> createPlayers(Map<String, Token> playerNames, int initialMoney) {
        return playerNames.keySet().stream().map(x -> new Player(x, initialMoney)).collect(Collectors.toList());
    }


    public static void main(String args[]) {
        System.out.println("Starting...");
        
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            spaceFactory.createSpace("IncomeTax")
        };
        
        Token[] playerTokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(playerTokens[0], 0);
            put(playerTokens[1], 0);
        }};
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.<Card>asList());
        Queue<Card> chanceCards = new LinkedList<>(Arrays.<Card>asList());
        Gameboard gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        Player[] players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(playerTokens);
        
        // pick first player
        int curPlayerIndex = 0;
        for (int i = 0; i < 2; i++) {
            // roll dice
            Player currentPlayer = players[curPlayerIndex];
            int steps = dice.roll();
            
            // move token
            Space space = gameboard.move(playerTokens[curPlayerIndex], steps);
            
            // based on space, take action
            // player buys space?
            // player picks card? (Community Chest, Chance)
            // player pays (Income Tax, Luxury Tax, etc)
            // player goes to jail
            // player do nothing
            
            List<PlayerAction> requiredActions = space.getRequiredActions();
            for (PlayerAction requiredAction: requiredActions) {
                try
                {
                    requiredAction.act(currentPlayer);
                }
                catch (PlayerActionException pae)
                {
                    pae.printStackTrace();
                }
            }

            Map<String, PlayerAction> playerActions = space.getPlayerOptions(currentPlayer);

            if (!playerActions.isEmpty()) {
                // always pick first action
                PlayerAction action = playerActions.get("Buy Property");
                try
                {
                    action.act(currentPlayer);
                }
                catch (PlayerActionException e)
                {
                    e.printStackTrace();
                }
                // TODO validate if action is valid (enough money, etc)
            }
            
            // TODO future: player buys property from another player
            
            curPlayerIndex = (curPlayerIndex + 1) % playerTokens.length;
        }
        
        System.out.println("end of game");
        System.out.println("player 0 " + players[0].getBalance());
        System.out.println("player 1 " + players[1].getBalance());
    }

    @Override
    public void start() {

    }
}
