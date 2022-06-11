package com.janaldous.monopoly;

import com.janaldous.monopoly.controller.GameController;
import com.janaldous.monopoly.controller.GameControllerImpl;
import com.janaldous.monopoly.core.bank.BankImpl;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.gamecontext.GameContextFactory;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.versions.factory.GameboardFactory;
import com.janaldous.monopoly.versions.original.OriginalCardFactory;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
import lombok.extern.java.Log;

import java.util.*;
import java.util.stream.Collectors;

@Log
public class GameImpl implements Game {

    @Override
    public Player start() {
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
            log.info(playerName + " moves to " + space.getName());

            boolean isPlayerStillPlaying = gameController.doRequiredPlayerActions();

            if (!isPlayerStillPlaying) {
                continue;
            }

            Map<String, PlayerAction> spaceOptions = gameController.getSpaceOptions();
            log.info("space options: " + spaceOptions.values());
            log.info(
                    "space options: "
                            + spaceOptions.values().stream()
                            .map(PlayerAction::getName)
                            .collect(Collectors.joining(",")));
            Optional<PlayerAction> first = spaceOptions.values().stream()
                    .filter(playerAction -> currentPlayer.shouldAct(playerAction))
                    .findFirst();
            if (first.isPresent()) {
                isPlayerStillPlaying = gameController.doCurrentPlayerAction(first.get());
            }

            if (!isPlayerStillPlaying) {
                continue;
            }

            gameController.finishPlayerTurn();
            log.info(playerName + " turn finished");
        }
        log.info(gameController.getCurrentPlayer().getName() + " wins");

        return gameController.getCurrentPlayer();
    }
}
