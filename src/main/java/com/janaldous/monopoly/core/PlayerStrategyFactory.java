package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.gamecontext.GameContext;

public class PlayerStrategyFactory {
    public static PlayerStrategy createStrategy(String strategyName, Player player, GameContext gameContext) {
        switch (strategyName) {
            case "normal":
                return new NormalPlayerStrategy(player, gameContext);
            default:
                return new NormalPlayerStrategy(player, gameContext);
        }

    }
}
