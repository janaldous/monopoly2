package com.janaldous.monopoly.core.bank;

import com.janaldous.monopoly.core.building.Hotel;
import com.janaldous.monopoly.core.building.House;
import com.janaldous.monopoly.core.player.Player;
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
  public void playerToPay(Player player, int amount) throws NotEnoughMoneyException {
    player.pay(amount);
    transactionRecords.add(new TransactionRecord(player.getName(), BANK_NAME, amount));
  }

  @Override
  public void payPlayer(Player player, int amount) {
    player.addMoney(amount);
    transactionRecords.add(new TransactionRecord(BANK_NAME, player.getName(), amount));
  }

  @Override
  public void transfer(Player fromPlayer, Player toPlayer, int amount) throws NotEnoughMoneyException {
    fromPlayer.pay(amount);
    toPlayer.addMoney(amount);
    transactionRecords.add(new TransactionRecord(fromPlayer.getName(), toPlayer.getName(), amount));
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
