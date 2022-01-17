package com.janaldous.monopoly.core;

import com.janaldous.monopoly.game.GameConfig;

public class PlayerFactory {

    private GameConfig config;

    public PlayerFactory(GameConfig config) {
        this.config = config;
    }

    public Player createPlayer(String name) {
        return new Player(name, config.initialMoney());
    }

}
