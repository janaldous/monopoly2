package com.janaldous.monopoly.controller;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import lombok.extern.java.Log;
import org.assertj.core.util.VisibleForTesting;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

/** Controls flow of game i.e. decides which player is next. Player can act through this object */
@Log
public class GameControllerImpl implements GameController {

  private final List<Player> players;
  private Gameboard gameboard;
  private final Dice dice;
  private final GameContext gameContext;
  private Player currentPlayer;
  private int currentPlayerIndex;

  public GameControllerImpl(GameContext gameContext) {
    this.gameContext = gameContext;
    this.gameboard = gameContext.getGameboard();
    this.players = gameContext.getPlayers();
    this.dice = gameContext.getDice();
    currentPlayerIndex = 0;
    currentPlayer = players.get(currentPlayerIndex);
  }

  @VisibleForTesting
  void setCurrentPlayerIndex(int index) {
    currentPlayerIndex = index;
    currentPlayer = players.get(index);
  }

  @Override
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public Space moveCurrentPlayer(int steps) {
    Token currentPlayerToken = gameContext.getPlayerToken(currentPlayer);
    return gameboard.moveBySteps(currentPlayerToken, steps);
  }

  @Override
  public int rollDice() {
    return dice.roll();
  }

  @Override
  public boolean doRequiredPlayerActions() {
    List<PlayerAction> requiredActions =
        gameContext.getPlayerSpace(currentPlayer).getRequiredActions().stream()
            .filter(playerAction -> playerAction.isValidAction(currentPlayer))
            .collect(Collectors.toList());
    log.info("required actions: " + requiredActions);
    for (PlayerAction playerAction: requiredActions) {
      try {
        playerAction.act(currentPlayer);
      } catch (PlayerActionException e) {
        log.log(Level.SEVERE, e, () -> currentPlayer.getName() + " is bankrupt");
        removeCurrentPlayerFromGame();
        finishPlayerTurn();
        return false;
      }
      log.info(currentPlayer.getName() + " is required to act: " + playerAction.getName());
    }
    return true;
  }

  @Override
  public Map<String, PlayerAction> getPlayerActionOptions() {
    return gameContext.getPlayerSpace(currentPlayer).getPlayerOptions(currentPlayer);
  }

  @Override
  public boolean doCurrentPlayerAction(PlayerAction playerAction) {
    try {
      playerAction.act(currentPlayer);
    } catch (PlayerActionException e) {
      log.log(Level.SEVERE, e, () -> currentPlayer.getName() + " is bankrupt");
      removeCurrentPlayerFromGame();
      finishPlayerTurn();
      return false;
    }
    return true;
  }

  private void removeCurrentPlayerFromGame() {
    players.remove(currentPlayerIndex);
    log.info(currentPlayer.getName() + " is removed from the game");
  }

  @Override
  public void finishPlayerTurn() {
    if (!isCurrentPlayerStillInTheGame()) {
      removeCurrentPlayerFromGame();
    } else {
      currentPlayerIndex++;
    }
    if (currentPlayerIndex >= players.size()) {
      currentPlayer = players.get(0);
      currentPlayerIndex = 0;
    } else {
      currentPlayer = players.get(currentPlayerIndex);
    }
  }

  @Override
  public boolean hasWinner() {
    return players.size() == 1;
  }

  private boolean isCurrentPlayerStillInTheGame() {
    return currentPlayer.getBalance() > 0;
  }
}
