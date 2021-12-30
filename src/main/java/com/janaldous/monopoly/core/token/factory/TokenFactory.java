package com.janaldous.monopoly.core.token.factory;

import com.janaldous.monopoly.core.token.CarToken;
import com.janaldous.monopoly.core.token.DogToken;
import com.janaldous.monopoly.core.token.Token;

public class TokenFactory {

    public Token createToken(String name) {
        switch (name) {
            case "dog":
                return new DogToken();
            case "car":
                return new CarToken();
            default:
                throw new IllegalArgumentException("Cannot recognize token name: " + name);
        }
    }

}
