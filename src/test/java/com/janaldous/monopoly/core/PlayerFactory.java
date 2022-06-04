package com.janaldous.monopoly.core;

import com.janaldous.monopoly.game.GameConfig;

public class PlayerFactory {

    private GameConfig config;

    public PlayerFactory(GameConfig config) {
        this.config = config;
    }

    public PlayerImpl createPlayer(String name) {
        return new PlayerImpl(name, config.initialMoney());
    }

}
