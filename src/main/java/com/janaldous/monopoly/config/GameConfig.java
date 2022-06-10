package com.janaldous.monopoly.config;

import com.janaldous.monopoly.core.token.Token;

import java.util.Map;

public interface GameConfig {

    Map<String, Token> getPlayerNames();

    int getInitialMoney();

    int getSalaryAmount();

    int getMaxNumOfApartments();
}
