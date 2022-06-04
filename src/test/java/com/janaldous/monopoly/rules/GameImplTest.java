package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.Game;
import com.janaldous.monopoly.GameImpl;
import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gameboard.GameboardImpl;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.versions.factory.GameboardFactory;
import com.janaldous.monopoly.core.token.factory.TokenFactory;
import com.janaldous.monopoly.game.GameConfig;
import com.janaldous.monopoly.game.USGameConfigImpl;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameImplTest {

    @Test
    void test() {
        GameFactory gameFactory = new GameFactory();
        gameFactory.createGame("original");

        TokenFactory tokenFactory = new TokenFactory();
        GameConfig config = USGameConfigImpl.USGameConfigBuilder.builder()
                .players(Map.of("A", tokenFactory.createToken("dog"), "B", tokenFactory.createToken("car")))
                .initialMoney(1500)
                .build();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(null, null);
        OriginalGameboardFactory originalGameboardFactory = new OriginalGameboardFactory(new SpaceFactory(playerActionFactory));
        GameboardFactory gameboardFactory = new GameboardFactory(originalGameboardFactory);
        Gameboard gameboard = gameboardFactory.createGameboard("original");
        PlayerFactory playerFactory = new PlayerFactory(config);
        List<Player> players = new ArrayList<>();
        players.add(playerFactory.createPlayer("A"));
        players.add(playerFactory.createPlayer("B"));
        Game game = new GameImpl(config, players, gameboard, bank);
        game.start();
    }

}
