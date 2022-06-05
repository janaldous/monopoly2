package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class PayPlayerAction implements PlayerAction {
  private Bank bank;
  private int amount;

  public PayPlayerAction(Bank bank, int amount) {
    this.bank = bank;
    this.amount = amount;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    bank.withdraw(amount);
    player.addMoney(amount);
    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Pay player";
  }
}
