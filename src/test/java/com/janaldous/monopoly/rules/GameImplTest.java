package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.Game;
import com.janaldous.monopoly.GameImpl;
import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.versions.factory.GameboardFactory;
import com.janaldous.monopoly.core.token.factory.TokenFactory;
import com.janaldous.monopoly.config.GameConfig;
import com.janaldous.monopoly.config.USGameConfigImpl;
import com.janaldous.monopoly.versions.original.OriginalCardFactory;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameImplTest {

    @Test
    void test() {
        TokenFactory tokenFactory = new TokenFactory();
        Token[] tokens = new Token[] {
                tokenFactory.createToken("dog"),
                tokenFactory.createToken("car")
        };
        GameConfig config = USGameConfigImpl.USGameConfigBuilder.builder()
                .players(Map.of("A", tokens[0], "B", tokens[1]))
                .initialMoney(1500)
                .build();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(null, null);
        OriginalGameboardFactory originalGameboardFactory = new OriginalGameboardFactory(new SpaceFactory(playerActionFactory));
        OriginalCardFactory originalCardFactory = new OriginalCardFactory(playerActionFactory);
        GameboardFactory gameboardFactory = new GameboardFactory(originalGameboardFactory, originalCardFactory);
        Gameboard gameboard = gameboardFactory.createGameboard("original", List.of(tokens));
        PlayerFactory playerFactory = new PlayerFactory(config);
        List<PlayerImpl> players = new ArrayList<>();
        players.add(playerFactory.createPlayer("A"));
        players.add(playerFactory.createPlayer("B"));
        Game game = new GameImpl(config, players, gameboard, bank);
        game.start();
    }

}
