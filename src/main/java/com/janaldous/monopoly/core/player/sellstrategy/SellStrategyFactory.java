package com.janaldous.monopoly.core.player.sellstrategy;

import com.janaldous.monopoly.core.player.Player;

public class SellStrategyFactory {
    public static SellPropertyStrategy createStrategy(String strategy, Player player) {
        switch (strategy) {
            case "cheapest":
            default:
                return new SellCheapestStrategy(player);
        }
    }
}
