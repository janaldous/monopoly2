package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.core.exception.*;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;

import java.util.*;

public class BuyHousePlayerAction implements PlayerAction {
  private static final int MAX_NUMBER_OF_APARTMENTS = 4;

  private final GameContext context;

  public BuyHousePlayerAction(GameContext context) {
    this.context = context;
  }

  @Override
  public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
    Token token = context.getPlayerToken(player);
    Gameboard gameboard = context.getGameboard();
    Space space = gameboard.getSpace(token);
    if (space instanceof ResidentialSpace) {
      ResidentialSpace property = (ResidentialSpace) space;

      if (!player.equals(property.getOwner())) {
        throw new PlayerActionException("This property is not owned by the player");
      }

      if (property.getHouseQty() >= MAX_NUMBER_OF_APARTMENTS) {
        throw new PlayerActionException(
            "You cannot have more than " + MAX_NUMBER_OF_APARTMENTS + " apartments");
      }

      if (playerCanAffordApartment(player, property)) {
        try {
          player.pay(property.getValue());
        } catch (NotEnoughMoneyException neme) {
          throw new PlayerActionException(neme);
        }
        property.addHouse();
      }
    }

    return Optional.empty();
  }

  @Override
  public boolean isValidAction(PlayerImpl player) {
    Token token = context.getPlayerToken(player);
    Gameboard gameboard = context.getGameboard();
    Space space = gameboard.getSpace(token);
    if (space instanceof ResidentialSpace) {
      ResidentialSpace property = (ResidentialSpace) space;

      if (!player.equals(property.getOwner())) {
        return false;
      }

      if (property.getHouseQty() >= MAX_NUMBER_OF_APARTMENTS) {
        return false;
      }

      if (playerCanAffordApartment(player, property)) {
        return true;
      }
    }
    return false;
  }

  private boolean playerCanAffordApartment(PlayerImpl player, ResidentialSpace property) {
    return player.getBalance() - property.getHouseValue() >= 0;
  }

  @Override
  public String getName() {
    return "Buy House";
  }
}
