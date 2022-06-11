package com.janaldous.monopoly.core.gamecontext;

import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.SellPropertyStrategy;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class SellCheapestStrategy implements SellPropertyStrategy {
    private final Player player;

    public SellCheapestStrategy(Player player) {
        this.player = player;
    }

    @Override
    public Optional<PropertySpace> chooseProperty() {
        if (player.getProperties().isEmpty()) return Optional.empty();
        return Optional.of(getCheapestProperty());
    }

    private PropertySpace getCheapestProperty() {
        return Collections.min(player.getProperties(), Comparator.comparingInt(PropertySpace::getValue));
    }
}
