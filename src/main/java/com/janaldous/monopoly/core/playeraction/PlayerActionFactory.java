package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.bank.Bank;
import com.janaldous.monopoly.core.gamecontext.GameContext;

public class PlayerActionFactory {
  private final Bank bank;
  private final GameContext context;

  public PlayerActionFactory(Bank bank, GameContext context) {
    this.bank = bank;
    this.context = context;
  }

    public PlayerAction createPlayerAction(String actionName) {
    switch (actionName) {
      case "CollectSalary":
        return new CollectSalaryAction(bank, context.getConfig());
      case "PickCommunityChestCard":
        return new PickCommunityChestCardAction(context);
      default:
        throw new IllegalArgumentException("Cannot recognize action " + actionName);
    }
  }

  public PlayerAction createChargePlayerAction(int amount) {
    return new ChargePlayerAction(amount, bank);
  }

  public PlayerAction createMoveByStepsPlayerAction(int steps) {
    return new MoveByStepsPlayerAction(steps, context);
  }

  public PlayerAction createMovePlayerAction(String propertyName) {
    return new MoveByPropertyNamePlayerAction(propertyName, context);
  }

  public PlayerAction createBuyPropertyAction() {
    return new BuyPropertyPlayerAction(context);
  }

  public PlayerAction createBuyHouseAction() {
    return new BuyHousePlayerAction(context);
  }

  public PlayerAction createPayRentAction() {
    return new PayRentPlayerAction(context);
  }

  public PlayerAction createSellPropertyAction() {
    return new SellPropertyPlayerAction(context);
  }

  public PlayerAction createChargeIncomeTaxPlayerAction(int fixedTax, int percentTax) {
    return new ChargeIncomeTaxPlayerAction(fixedTax, percentTax);
  }

  public PlayerAction createGoToJailAction() {
    return new GoToJailPlayerAction(context);
  }

  public PlayerAction createMoveNearestUtilityPlayerAction() {
    return new MoveToNearestUtilityPlayerAction(context, createBuyPropertyAction());
  }

  public PlayerAction createMoveNearestRailroad() {
    return new MoveToNearestRailroadPlayerAction(context, createBuyPropertyAction());
  }

  public PlayerAction createPayPlayer(int amount) {
    return new PayPlayerAction(bank, amount);
  }

  public PlayerAction createGetOutOfJailAction() {
    return new GetOutOfJailPLayerAction();
  }

  public PlayerAction createGetOutOfJailFree() {
    return new PickGetOutOfJailFreeCardAction();
  }

  public PlayerAction createMakeGeneralRepairsAction() {
    return new MakeGeneralRepairsAction(25, 100, bank);
  }

  public PlayerAction createPayAllPlayersAction(int amount) {
    return new PayAllPlayersAction(context, amount);
  }
}
