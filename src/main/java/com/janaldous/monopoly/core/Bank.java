package com.janaldous.monopoly.core;

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

  void pay(Player player, int value) throws NotEnoughMoneyException;
}
