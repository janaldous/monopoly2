package com.janaldous.monopoly;

import com.janaldous.monopoly.controller.GameController;
import com.janaldous.monopoly.controller.GameControllerImpl;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.gamecontext.GameContextFactory;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.versions.factory.GameboardFactory;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

@Log
public class Tester {

  @Test
  public void test() {

    GameContext gameContext = new GameContextFactory().createGameContextWithoutGameBoard();
    PlayerActionFactory playerActionFactory = new PlayerActionFactory(new BankImpl(), gameContext);
    GameboardFactory gameboardFactory =
        new GameboardFactory(
            new OriginalGameboardFactory(
                new SpaceFactory(playerActionFactory)));
    gameContext.setGameboard(gameboardFactory.createGameboard("original", gameContext.getTokens()));
    GameController gameController = new GameControllerImpl(gameContext);

    for (int i = 0; i < 5; i++) {
      Player currentPlayer = gameController.getCurrentPlayer();
      log.info("Current player " + currentPlayer);

      Space space = gameController.moveCurrentPlayer(gameContext.getDice().roll());
      String playerName = currentPlayer.getName();
      log.info(playerName + " moves to " + space);

      gameController.doRequiredPlayerActions();

      gameController.finishPlayerTurn();
      log.info(playerName + " turn finished");
    }
  }
}
