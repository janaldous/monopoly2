package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class ChargeIncomeTaxPlayerAction implements PlayerAction {
  private int fixedTax;
  private int percentTax;

  public ChargeIncomeTaxPlayerAction(int fixedTax, int percentTax) {
    this.fixedTax = fixedTax;
    this.percentTax = percentTax;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    int fixedTaxAmount = fixedTax;
    int percentTaxAmount = player.getBalance() * (percentTax / 100);

    int payMinimum = Math.min(fixedTaxAmount, percentTaxAmount);

    try {
      player.pay(payMinimum);
    } catch (NotEnoughMoneyException e) {
      throw new PlayerActionException(e);
    }

    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Charge Income Tax";
  }
}
