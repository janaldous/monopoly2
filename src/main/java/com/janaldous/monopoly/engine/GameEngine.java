package com.janaldous.monopoly.engine;

import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.playeraction.PlayerAction;

public interface GameEngine {

    void start();

    Player getCurrentPlayer();

    void doCurrentPlayerAction(PlayerAction playerAction);

    

}
