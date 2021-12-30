package com.janaldous.monopoly.versions.factory;

import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gameboard.GameboardImpl;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameboardFactory {

    public GameboardFactory() {}

    public Gameboard createGameboard(String name) {
        switch (name) {
            case "original":
                return new GameboardImpl();
        }
    }

    private Map<Token, Integer> createTokenPositions(List<Token> tokens) {
        return tokens.stream().collect(Collectors.toMap(x -> x, x -> 0));
    }
}
