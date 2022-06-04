package com.janaldous.monopoly.core.space;
import com.janaldous.monopoly.core.PropertyGroup;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.rentstrategy.RentStrategy;

import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class PropertySpace extends Space
{
    protected PlayerImpl owner;
    protected final int value;
    protected final List<PlayerAction> playerActionOptions;
    protected final int siteOnlyRent;
    protected RentStrategy strategy;
    protected final PropertyGroup colorGroup;

    
    protected PropertySpace(String name, 
                            int value,
                            PropertyGroup colorGroup,
                            int siteOnlyRent,
                            List<PlayerAction> requiredActions,
                            List<PlayerAction> playerActionOptions)
    {
        super(name, requiredActions);
        this.colorGroup = colorGroup;
        this.value = value;
        this.siteOnlyRent = siteOnlyRent;
        this.playerActionOptions = playerActionOptions;
    }
    
    public void setOwner(PlayerImpl owner) {
        this.owner = owner;
    }
    
    public PlayerImpl getOwner() {
        return owner;
    }
    
    public void removeOwner() {
        owner = null;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setStrategy(RentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public RentStrategy getStrategy() {
        return strategy;
    }
    
    public int getRent() {
        if (strategy == null) throw new IllegalStateException("strategy not initialized");
        return strategy.calculateRent();
    }
    
    @Override
    public Map<String, PlayerAction> getPlayerOptions(final PlayerImpl player) {
        return playerActionOptions.stream()
            .filter(pa -> pa.isValidAction(player))
            .collect(Collectors.toMap(PlayerAction::getName, Function.identity()));
    }
    
    public PropertyGroup getPropertyGroup() {
        return colorGroup;
    }
}
