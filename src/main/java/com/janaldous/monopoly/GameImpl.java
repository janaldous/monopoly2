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
        gameController = new GameControllerImpl(gameContext);
    }

    @Override
    public Player start() {
        while (!gameController.hasWinner()) {
            currentPlayer = gameController.getCurrentPlayer();
            String playerName = currentPlayer.getName();
            log.info("Current player " + playerName);

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
        log.info(gameController.getCurrentPlayer().getName() + " wins");

        return gameController.getCurrentPlayer();
    }

    private void finishPlayerTurn() {
        gameController.finishPlayerTurn();
        log.info(currentPlayer.getName() + " turn finished");
    }

    private boolean actOnPlayerOptions() {
        List<PlayerAction> playerActionOptions = gameController.getActionOptions();
        Optional<PlayerAction> possiblePlayerAction = playerActionOptions.stream()
                .filter(playerAction -> currentPlayer.shouldAct(playerAction))
                .findFirst();
        if (possiblePlayerAction.isPresent()) {
            return gameController.doCurrentPlayerAction(possiblePlayerAction.get());
        }
        return true;
    }

    private boolean actOnSpaceOptions() {
        Map<String, PlayerAction> spaceOptions = gameController.getSpaceOptions();
        log.info("space options: " + spaceOptions.values());
        log.info(
                "space options: "
                        + spaceOptions.values().stream()
                        .map(PlayerAction::getName)
                        .collect(Collectors.joining(",")));
        Optional<PlayerAction> possibleSpaceAction = spaceOptions.values().stream()
                .filter(playerAction -> currentPlayer.shouldAct(playerAction))
                .findFirst();
        if (possibleSpaceAction.isPresent()) {
            return gameController.doCurrentPlayerAction(possibleSpaceAction.get());
        }
        return true;
    }

    private boolean movePlayer() {
        Space space = gameController.moveCurrentPlayer(gameContext.getDice().roll());
        log.info(currentPlayer.getName() + " moves to " + space.getName());

        return gameController.doRequiredPlayerActions();
    }
}
