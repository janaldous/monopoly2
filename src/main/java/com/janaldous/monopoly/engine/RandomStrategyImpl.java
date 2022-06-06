package com.janaldous.monopoly.engine;

import java.util.Random;

public class RandomStrategyImpl implements RandomStrategy {
    @Override
    public int generateRandomNumber(int maxNumber) {
        return new Random().nextInt(maxNumber);
    }
}
