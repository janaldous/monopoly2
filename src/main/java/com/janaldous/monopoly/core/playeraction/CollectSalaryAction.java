package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.config.GameConfig;

import java.util.Optional;

public class CollectSalaryAction implements PlayerAction {
  private final Bank bank;
  private GameConfig gameConfig;

  public CollectSalaryAction(Bank bank, GameConfig gameConfig) {
    this.bank = bank;
    this.gameConfig = gameConfig;
  }

  @Override
  public Optional<PlayerAction> act(Player player) {
    bank.payPlayer(player, gameConfig.getSalaryAmount());

    return Optional.empty();
  }

  @Override
  public String getName() {
    return "";
  }
}
