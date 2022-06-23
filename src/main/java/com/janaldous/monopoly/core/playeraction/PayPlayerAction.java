package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.bank.Bank;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.List;
import java.util.Optional;

public class PayPlayerAction implements PlayerAction {
  private Bank bank;
  private int amount;

  public PayPlayerAction(Bank bank, int amount) {
    this.bank = bank;
    this.amount = amount;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    bank.payPlayer(player, amount);
    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Pay player";
  }

  @Override
  public String toString() {
    return getName();
  }
}
