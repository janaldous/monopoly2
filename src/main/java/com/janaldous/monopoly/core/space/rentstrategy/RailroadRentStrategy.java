package com.janaldous.monopoly.core.space.rentstrategy;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.space.RailroadSpace;

public class RailroadRentStrategy implements RentStrategy {

    private int properties;
    private final RailroadSpace railroad;

    public RailroadRentStrategy(int properties, RailroadSpace railroad) {
        this.properties = properties;
        this.railroad = railroad;
    }

    @Override
    public int calculateRent() {
        return railroad.getRailroadRent(properties);
    }
}
