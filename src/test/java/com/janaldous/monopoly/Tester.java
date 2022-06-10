package com.janaldous.monopoly;

import com.janaldous.monopoly.controller.GameController;
import com.janaldous.monopoly.controller.GameControllerImpl;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.gamecontext.GameContextFactory;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.versions.factory.GameboardFactory;
import com.janaldous.monopoly.versions.original.OriginalCardFactory;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

@Log
public class Tester {

  @Test
  public void test() {

    GameContext gameContext = new GameContextFactory().createGameContextWithoutGameBoard();
    PlayerActionFactory playerActionFactory = new PlayerActionFactory(new BankImpl(), gameContext);
    GameboardFactory gameboardFactory =
        new GameboardFactory(
            new OriginalGameboardFactory(new SpaceFactory(playerActionFactory)),
            new OriginalCardFactory(playerActionFactory));
    gameContext.setGameboard(gameboardFactory.createGameboard("original", gameContext.getTokens()));
    GameController gameController = new GameControllerImpl(gameContext);

    while (!gameController.hasWinner()) {
      Player currentPlayer = gameController.getCurrentPlayer();
      String playerName = currentPlayer.getName();
      log.info("Current player " + playerName);

      Space space = gameController.moveCurrentPlayer(gameContext.getDice().roll());
      log.info(playerName + " moves to " + space);

      gameController.doRequiredPlayerActions();

      Map<String, PlayerAction> playerActionOptions = gameController.getPlayerActionOptions();
      log.info("player options: " + playerActionOptions.values().toString());
      log.info(
          "player options: "
              + playerActionOptions.values().stream()
                  .map(PlayerAction::getName)
                  .collect(Collectors.joining(",")));
      playerActionOptions.values().stream()
          .filter(playerAction -> currentPlayer.shouldAct(playerAction))
          .forEach(
              playerAction -> {
                try {
                  playerAction.act(currentPlayer);
                } catch (PlayerActionException e) {
                  throw new RuntimeException(e);
                }
              });

      gameController.finishPlayerTurn();
      log.info(playerName + " turn finished");
    }
    log.info(gameController.getCurrentPlayer().getName() + " wins");
  }
}
