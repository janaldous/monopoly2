package com.janaldous.monopoly.controller;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.Space;

import java.util.Map;

public interface GameController {

    Player getCurrentPlayer();

    Space moveCurrentPlayer(int steps);

    int rollDice();

    /**
     * Do required player action. If player is bankkrupt, returns false
     * @return if player is still allowed to play
     */
    boolean doRequiredPlayerActions();

    Map<String, PlayerAction> getSpaceOptions();

    /**
     * Do player action. If player is bankkrupt, returns false
     * @return if player is still allowed to play
     */
    boolean doCurrentPlayerAction(PlayerAction playerAction);

    void finishPlayerTurn();

    boolean hasWinner();
}
