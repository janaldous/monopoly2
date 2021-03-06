package com.janaldous.monopoly.config;

import com.janaldous.monopoly.core.token.Token;

import java.util.Map;

public class USGameConfigImpl implements GameConfig {

    public static final int DEFAULT_INITIAL_MONEY = 200;
    private final Map<String, Token> playerNames;
    private final int initialMoney;

    public USGameConfigImpl(Map<String, Token> playerNames) {
        this.playerNames = playerNames;
        this.initialMoney = DEFAULT_INITIAL_MONEY;
    }

    public USGameConfigImpl(Map<String, Token> playerNames, int initialMoney) {
        this.playerNames = playerNames;
        this.initialMoney = initialMoney;
    }

    @Override
    public Map<String, Token> getPlayerNames() {
        return playerNames;
    }

    @Override
    public int getInitialMoney() {
        return initialMoney;
    }

    @Override
    public int getSalaryAmount() {
        return 200;
    }

    @Override
    public int getMaxNumOfApartments() {
        return 4;
    }

    public static class USGameConfigBuilder {
        private Map<String, Token> playerNames;
        private int initialMoney;

        public static USGameConfigBuilder builder() {
            return new USGameConfigBuilder();
        }

        public USGameConfigBuilder players(Map<String, Token> playerNames) {
            this.playerNames = playerNames;
            return this;
        }

        public USGameConfigBuilder initialMoney(int initialMoney) {
            this.initialMoney = initialMoney;
            return this;
        }

        public GameConfig build() {
            return new USGameConfigImpl(playerNames, initialMoney);
        }
    }

}
