package com.janaldous.monopoly.controller;

public class GameException extends RuntimeException {
    public GameException(Exception e) {
        super(e);
    }
}
