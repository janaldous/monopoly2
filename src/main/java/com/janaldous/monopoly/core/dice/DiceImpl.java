package com.janaldous.monopoly.core.dice;

import java.util.Random;

public class DiceImpl implements Dice
{
    private final int numberOfDice;
    private int value;
    private DiceStrategy strategy;
    
    public DiceImpl(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        // default strategy is random number generator
        this.strategy = () -> new Random().nextInt(6 * numberOfDice);
    }
    
    @Override
    public int roll() {
        value = strategy.execute();
        return value;
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
    public void setStrategy(DiceStrategy strategy) {
        this.strategy = strategy;
    }
}
