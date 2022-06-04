package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.token.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameContextImpl implements GameContext {
  private Gameboard gameboard;
  private Dice dice;
  private Player[] players;
  private Token[] tokens;
  private Map<Player, Integer> playerToPlayerIndex;

  public GameContextImpl() {}

  public GameContextImpl(Gameboard gameboard, Dice dice, PlayerImpl[] players, Token[] tokens) {
    if (players.length != tokens.length)
      throw new IllegalStateException("players and tokens should have same length");
    this.gameboard = gameboard;
    this.dice = dice;
    this.players = players;
    this.tokens = tokens;

    // populate map
    populateMap(players);
  }

  @Override
  public Gameboard getGameboard() {
    if (gameboard == null) throw new IllegalStateException("gameboard not initialized");
    return gameboard;
  }

  @Override
  public Dice getDice() {
    if (dice == null) throw new IllegalStateException("dice not initialized");
    return dice;
  }

  @Override
  public List<Player> getPlayers() {
    return List.of(players);
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

  public void setPlayers(PlayerImpl[] players) {
    this.players = players;
    populateMap(players);
  }

  public void setTokens(Token[] tokens) {
    this.tokens = tokens;
  }

  private void populateMap(PlayerImpl[] players) {
    this.playerToPlayerIndex = new HashMap<>();
    for (int i = 0; i < players.length; i++) {
      playerToPlayerIndex.put(players[i], i);
    }
  }
}
