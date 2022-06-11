package com.janaldous.monopoly.core.player.playerstrategy;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.player.playerstrategy.NormalPlayerStrategy;
import com.janaldous.monopoly.core.player.playerstrategy.PlayerStrategy;

public class PlayerStrategyFactory {
    public static PlayerStrategy createStrategy(String strategyName, Player player, GameContext gameContext) {
        switch (strategyName) {
            case "normal":
            default:
                return new NormalPlayerStrategy(player, gameContext);
        }

    }
}
