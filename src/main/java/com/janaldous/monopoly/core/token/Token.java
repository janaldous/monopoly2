package com.janaldous.monopoly.core.token;


import lombok.Getter;

public class Token {
    @Getter
    private final String name;

    protected Token(String name) {
        this.name = name;
    }
}
