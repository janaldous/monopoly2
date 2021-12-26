package com.janaldous.monopoly.core.space.rentstrategy;


import com.janaldous.monopoly.core.GameContext;

public class NormalUtilityRentStrategy implements RentStrategy
{
    protected final GameContext context;
    
    public NormalUtilityRentStrategy(GameContext context) {
        this.context = context;
    }
    
    @Override
    public int calculateRent() {
        return context.getDice().getValue() * 4;
    }
}
