package com.janaldous.monopoly.core;


import com.janaldous.monopoly.core.dice.Dice;

import java.util.List;

/**
 * Write a description of interface GameContext here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface GameContext
{
    Gameboard getGameboard();
    Token getPlayerToken(Player player);
    Dice getDice();

    List<Player> getPlayers();
}
