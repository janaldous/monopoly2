package com.janaldous.monopoly.core.dice;

public interface Dice
{
    int roll();
    int getValue();
    void setStrategy(DiceStrategy strategy);
}
