package com.janaldous.monopoly.engine;

import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.Space;

import java.util.List;
import java.util.Map;

public interface GameController {

    void start();

    Player getCurrentPlayer();

    Space moveCurrentPlayer(int steps);

    int rollDice();

    List<PlayerAction> getRequiredPlayerActions();

    Map<String, PlayerAction> getPlayerActionOptions();

    void doCurrentPlayerAction(PlayerAction playerAction);

}
