package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.Game;
import com.janaldous.monopoly.GameImpl;
import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.versions.factory.GameboardFactory;
import com.janaldous.monopoly.core.token.factory.TokenFactory;
import com.janaldous.monopoly.game.GameConfig;
import com.janaldous.monopoly.game.USGameConfigImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GameImplTest {

    @Test
    void test() {
        TokenFactory tokenFactory = new TokenFactory();
        GameConfig config = USGameConfigImpl.USGameConfigBuilder.builder()
                .players(Map.of("A", tokenFactory.createToken("dog"), "B", tokenFactory.createToken("car")))
                .initialMoney(1500)
                .build();
        Bank bank = new BankImpl();
        GameboardFactory gameboardFactory = new GameboardFactory();
        Gameboard gameboard = gameboardFactory.createGameboard("us");
        Game game = new GameImpl(config, gameboard, bank);
        game.start();
    }

}
