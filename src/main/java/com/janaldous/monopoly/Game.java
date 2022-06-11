package com.janaldous.monopoly;

import com.janaldous.monopoly.core.Player;

public interface Game {

    /**
     * Play whole game.
     * @return winning player
     */
    Player start();
}
