package com.janaldous.monopoly.core;

public interface Dice
{
    int roll();
    int getValue();
    void setStrategy(DiceStrategy strategy);
}
