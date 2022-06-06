package com.janaldous.monopoly.engine;

public class GameException extends RuntimeException {
    public GameException(Exception e) {
        super(e);
    }
}
