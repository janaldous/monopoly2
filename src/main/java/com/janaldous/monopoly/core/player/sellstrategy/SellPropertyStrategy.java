package com.janaldous.monopoly.core.player.sellstrategy;

import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.Optional;

public interface SellPropertyStrategy {
    /**
     * @return property to sell if player has properties
     */
    Optional<PropertySpace> chooseProperty();
}
