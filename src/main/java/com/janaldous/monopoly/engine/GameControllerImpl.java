package com.janaldous.monopoly.engine;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Map;

/**
 * Controls flow of game i.e. decides which player is next. Player can act through this object
 */
public class GameControllerImpl implements GameController {

    private final List<Player> players;
    private final Gameboard gameboard;
    private final Dice dice;
    private final GameContext gameContext;
    private Player currentPlayer;

    public GameControllerImpl(GameContext gameContext, Dice dice) {
        this.gameContext = gameContext;
        this.gameboard = gameContext.getGameboard();
        this.players = gameContext.getPlayers();
        this.dice = dice;
    }

    @Override
    public void start() {
        currentPlayer = players.get(0);
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
    public List<PlayerAction> getRequiredPlayerActions() {
        return gameContext.getPlayerSpace(currentPlayer).getRequiredActions();
    }

    @Override
    public Map<String, PlayerAction> getPlayerActionOptions() {
        return gameContext.getPlayerSpace(currentPlayer).getPlayerOptions(currentPlayer);
    }

    @Override
    public void doCurrentPlayerAction(PlayerAction playerAction) {
        try {
            playerAction.act(currentPlayer);
        } catch (PlayerActionException e) {
            throw new GameException(e);
        }
    }
}
