package com.janaldous.monopoly.core.gamecontext;

import com.janaldous.monopoly.config.GameConfig;
import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import lombok.extern.java.Log;

import java.util.*;

@Log
public class GameContextImpl implements GameContext {
  private Gameboard gameboard;
  private Dice dice;
  private Player[] players;
  private Token[] tokens;
  private Map<Player, Integer> playerToPlayerIndex;
  private Bank bank;
  private GameConfig gameConfig;

  public GameContextImpl() {}

  public GameContextImpl(Dice dice,
                         PlayerImpl[] players,
                         Token[] tokens,
                         Bank bank,
                         GameConfig gameConfig) {
    this(null, dice, players, tokens, bank, gameConfig);
  }

  public GameContextImpl(
      Gameboard gameboard,
      Dice dice,
      PlayerImpl[] players,
      Token[] tokens,
      Bank bank,
      GameConfig gameConfig) {
    this.bank = bank;
    this.gameConfig = gameConfig;
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
    return new ArrayList<>(Arrays.asList(players));
  }

  @Override
  public Bank getBank() {
    return bank;
  }

  @Override
  public GameConfig getConfig() {
    return gameConfig;
  }

  @Override
  public List<Token> getTokens() {
    return List.of(tokens);
  }

  @Override
  public Token getPlayerToken(Player player) {
    int index = playerToPlayerIndex.get(player);
    return tokens[index];
  }

  @Override
  public Space getPlayerSpace(Player player) {
    Token token = getPlayerToken(player);
    return gameboard.getSpace(token);
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
