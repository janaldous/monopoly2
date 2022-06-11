package com.janaldous.monopoly.core.gamecontext;

import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.SellPropertyStrategy;

public class SellStrategyFactory {
    public static SellPropertyStrategy createStrategy(String strategy, Player player) {
        switch (strategy) {
            case "cheapest":
            default:
                return new SellCheapestStrategy(player);
        }
    }
}
