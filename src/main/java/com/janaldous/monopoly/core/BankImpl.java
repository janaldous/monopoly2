package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;

public class BankImpl implements Bank {

  private static final String BANK_NAME = "BANK";
  private List<TransactionRecord> transactionRecords;

  public BankImpl() {
    transactionRecords = new ArrayList<>();
  }

  public Hotel buyHotel(int value) {
    return null;
  }

  @Override
  public void pay(Player player, int value) throws NotEnoughMoneyException {
    player.pay(value);
    transactionRecords.add(new TransactionRecord(player.getName(), BANK_NAME, value));
  }

  public House buyHouse(int value) {
    return null;
  }

  public int withdraw(int amount) {
    return amount;
  }

  public void deposit(int value) {}

  private record TransactionRecord(String fromPlayer, String toPlayer, int value) {}
}
