package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.space.ResidentialSpace;

import java.util.Optional;

public class MakeGeneralRepairsAction implements PlayerAction {

  private int houseRepairCost;
  private int hotelRepairCost;
  private Bank bank;

  public MakeGeneralRepairsAction(int houseRepairCost, int hotelRepairCost, Bank bank) {
    this.houseRepairCost = houseRepairCost;
    this.hotelRepairCost = hotelRepairCost;
    this.bank = bank;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    int totalRepairCost =
        player.getProperties().stream()
            .filter(p -> p instanceof ResidentialSpace)
            .map(p -> (ResidentialSpace) p)
            .filter(r -> r.getHouseQty() > 0 || r.getHotelQty() > 0)
            .mapToInt(
                r -> (r.getHouseQty() * houseRepairCost) + (r.getHotelQty() * hotelRepairCost))
            .reduce(0, (t, elem) -> t + elem);

    try {
      player.pay(totalRepairCost);
      bank.deposit(totalRepairCost);
    } catch (NotEnoughMoneyException e) {
      throw new PlayerActionException(e);
    }

    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Make general repairs";
  }
}
