package com.janaldous.monopoly.versions.factory;

import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gameboard.GameboardImpl;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameboardFactory {

  private OriginalGameboardFactory originalGameboardFactory;

  public GameboardFactory(OriginalGameboardFactory originalGameboardFactory) {
    this.originalGameboardFactory = originalGameboardFactory;
  }

  public Gameboard createGameboard(String name) {
    switch (name) {
      case "original":
        return new GameboardImpl(
            originalGameboardFactory.createSpaces(),
            createTokenPositions(Collections.emptyList()),
            null,
            null,
            null);
      default:
        return null;
    }
  }

  private Map<Token, Integer> createTokenPositions(List<Token> tokens) {
    return tokens.stream().collect(Collectors.toMap(x -> x, x -> 0));
  }
}
