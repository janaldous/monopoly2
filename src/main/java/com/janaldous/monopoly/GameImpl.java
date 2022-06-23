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

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Log
public class GameImpl implements Game {

    private final GameController gameController;
    private final GameContext gameContext;
    private Player currentPlayer;

    public GameImpl() {
        gameContext = new GameContextFactory().createGameContextWithoutGameBoard();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(new BankImpl(), gameContext);
        GameboardFactory gameboardFactory =
                new GameboardFactory(
                        new OriginalGameboardFactory(new SpaceFactory(playerActionFactory)),
                        new OriginalCardFactory(playerActionFactory));
        gameContext.setGameboard(gameboardFactory.createGameboard("original", gameContext.getTokens()));
        gameController = new GameControllerImpl(gameContext, playerActionFactory);
    }

    @Override
    public Player start() {
    for (int i = 0; i < 1000 && !gameController.hasWinner(); i++) {
            currentPlayer = gameController.getCurrentPlayer();
            String playerName = currentPlayer.getName();
            log.info("current player <" + playerName + ">");

            boolean isPlayerStillPlaying = movePlayer();

            if (!isPlayerStillPlaying) {
                continue;
            }

            isPlayerStillPlaying = actOnSpaceOptions();

            if (!isPlayerStillPlaying) {
                continue;
            }

            isPlayerStillPlaying = actOnPlayerOptions();

            if (!isPlayerStillPlaying) {
                continue;
            }

            finishPlayerTurn();
        }

        if (!gameController.hasWinner()) {
            log.info("no winner within 1000 turns");
            return null;
        } else {
            log.info(gameController.getCurrentPlayer().getName() + " wins");
            return gameController.getCurrentPlayer();
        }
    }

    private void finishPlayerTurn() {
        gameController.finishPlayerTurn();
        log.info(MessageFormat.format("<{0}> turn furnished", currentPlayer.getName()));
    }

    private boolean actOnPlayerOptions() {
        List<PlayerAction> playerActionOptions = gameController.getActionOptions();
        log.info("player options: " + playerActionOptions);
        Optional<PlayerAction> possiblePlayerAction = playerActionOptions.stream()
                .filter(playerAction -> currentPlayer.shouldAct(playerAction))
                .findFirst();
        if (possiblePlayerAction.isPresent()) {
            log.info("player acting on player option <" + possiblePlayerAction.get().getName() + ">");
            return gameController.doCurrentPlayerAction(possiblePlayerAction.get());
        }
        return true;
    }

    private boolean actOnSpaceOptions() {
        Map<String, PlayerAction> spaceOptions = gameController.getSpaceOptions();
        log.info("space options: " + spaceOptions.values());
        Optional<PlayerAction> possibleSpaceAction = spaceOptions.values().stream()
                .filter(playerAction -> currentPlayer.shouldAct(playerAction))
                .findFirst();
        if (possibleSpaceAction.isPresent()) {
            log.info("player acting on space option <" + possibleSpaceAction.get().getName() + ">");
            return gameController.doCurrentPlayerAction(possibleSpaceAction.get());
        }
        return true;
    }

    private boolean movePlayer() {
        Space space = gameController.moveCurrentPlayer(gameContext.getDice().roll());
        log.info(MessageFormat.format("<{0}> moves to <{1}>", currentPlayer.getName(), space.getName()));

        return gameController.doRequiredPlayerActions();
    }
}
