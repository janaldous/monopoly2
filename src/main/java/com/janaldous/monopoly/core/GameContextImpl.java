package com.janaldous.monopoly.core;
import java.util.*;

public class GameContextImpl implements GameContext
{
    private Gameboard gameboard;
    private Dice dice;
    private Player[] players;
    private Token[] tokens;
    private Map<Player, Integer> playerToPlayerIndex;
    
    public GameContextImpl() {
    }
    
    public GameContextImpl(Gameboard gameboard, Dice dice, Player[] players, Token[] tokens)
    {
        assert players.length == tokens.length;
        
        this.gameboard = gameboard;
        this.dice = dice;
        this.players = players;
        this.tokens = tokens;
        
        // populate map
        populateMap(players);
    }
    
    @Override
    public Gameboard getGameboard() {
        assert gameboard != null;
        return gameboard;
    }
    
    @Override
    public Dice getDice() {
        assert dice != null;
        return dice;
    }
    
    @Override
    public Token getPlayerToken(Player player) {
        int index = playerToPlayerIndex.get(player);
        return tokens[index];
    }
    
    public void setDice(Dice dice) {
        this.dice = dice;
    }
    
    public void setGameboard(Gameboard gameboard) {
        this.gameboard = gameboard;
    }
    
    public void setPlayers(Player[] players) {
        this.players = players;
        populateMap(players);
    }
    
    public void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }
    
    private void populateMap(Player[] players) {
        this.playerToPlayerIndex = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            playerToPlayerIndex.put(players[i], i);
        }
    }
}
