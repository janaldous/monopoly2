package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.Optional;

public interface SellPropertyStrategy {
    Optional<PropertySpace> chooseProperty();
}
