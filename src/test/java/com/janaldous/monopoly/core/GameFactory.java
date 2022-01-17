package com.janaldous.monopoly.core;

import com.janaldous.monopoly.Game;
import com.janaldous.monopoly.GameImpl;

public class GameFactory {

    private PlayerFactory playerFactory;

    public GameFactory() {
    }

    public Game createGame(String name) {
        switch (name) {
            case "original":
                return new GameImpl();
            default:
                throw new IllegalArgumentException("Cannot find game with name " + name);
        }
    }
}
