package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.bank.Bank;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class ChargePlayerAction implements PlayerAction {
  private final int cost;
  private final Bank bank;

  public ChargePlayerAction(int cost, Bank bank) {
    this.cost = cost;
    this.bank = bank;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    try {
      bank.playerToPay(player, cost);
    } catch (NotEnoughMoneyException e) {
      throw new PlayerActionException(e);
    }

    return Optional.empty();
  }

  @Override
  public String getName() {
    return "";
  }
}
