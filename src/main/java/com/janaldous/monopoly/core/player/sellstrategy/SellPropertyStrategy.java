package com.janaldous.monopoly.core.player.sellstrategy;

import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.Optional;

public interface SellPropertyStrategy {
    Optional<PropertySpace> chooseProperty();
}
