package com.janaldous.monopoly.game;

import com.janaldous.monopoly.core.token.Token;

import java.util.Map;

public interface GameConfig {

    Map<String, Token> getPlayerNames();

    int initialMoney();

    int getSalaryAmount();

    int getMaxNumOfApartments();
}
