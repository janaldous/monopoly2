package com.janaldous.monopoly.core.gamecontext;

import com.janaldous.monopoly.config.GameConfig;
import com.janaldous.monopoly.config.USGameConfigImpl;
import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.dice.DiceImpl;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.token.CarToken;
import com.janaldous.monopoly.core.token.DogToken;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.versions.factory.GameboardFactory;

import java.util.*;

public class GameContextFactory {

  public GameContextFactory() {
  }

  public GameContext createGameContextWithoutGameBoard() {
    Token[] tokens = new Token[] {new CarToken(), new DogToken()};
    Dice dice = new DiceImpl(2);
    Bank bank = new BankImpl();

    Map<String, Token> playerNames = new HashMap<>() {{
      put("player 1 name", tokens[0]);
      put("player 2 name", tokens[1]);
    }};
    GameConfig gameConfig = new USGameConfigImpl(playerNames);
    PlayerImpl[] players =
        new PlayerImpl[] {
          new PlayerImpl("player 1", gameConfig.getInitialMoney()),
          new PlayerImpl("player 2", gameConfig.getInitialMoney())
        };
    return new GameContextImpl(dice, players, tokens, bank, gameConfig);
  }

}
