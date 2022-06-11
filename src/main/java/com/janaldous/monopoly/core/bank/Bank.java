package com.janaldous.monopoly.core.bank;

import com.janaldous.monopoly.core.building.Hotel;
import com.janaldous.monopoly.core.building.House;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;

/**
 * Write a description of interface Bank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Bank {
  void deposit(int amount);

  int withdraw(int amount);

  House buyHouse(int amount);

  Hotel buyHotel(int amount);

  void playerToPay(Player player, int amount) throws NotEnoughMoneyException;

  void payPlayer(Player player, int amount);

  void transfer(Player fromPlayer, Player toPlayer, int amount) throws NotEnoughMoneyException;
}
