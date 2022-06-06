package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.config.GameConfig;

import java.util.List;

/**
 * Write a description of interface GameContext here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface GameContext {
  Gameboard getGameboard();

  Token getPlayerToken(Player player);

  Space getPlayerSpace(Player player);

  Dice getDice();

  List<Player> getPlayers();

  Bank getBank();

  GameConfig getConfig();
}
