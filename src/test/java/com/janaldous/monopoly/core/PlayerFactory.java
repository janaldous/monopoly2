package com.janaldous.monopoly.core;

import com.janaldous.monopoly.config.GameConfig;
import com.janaldous.monopoly.core.player.PlayerImpl;

public class PlayerFactory {

    private GameConfig config;

    public PlayerFactory(GameConfig config) {
        this.config = config;
    }

    public PlayerImpl createPlayer(String name) {
        return new PlayerImpl(name, config.getInitialMoney());
    }

}
