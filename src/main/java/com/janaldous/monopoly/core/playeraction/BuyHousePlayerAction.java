package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Optional;

public class BuyHousePlayerAction implements PlayerAction {
  private final GameContext context;
  private final PropertySpace property;

  public BuyHousePlayerAction(GameContext context) {
    this.context = context;
    this.property = null;
  }

  public BuyHousePlayerAction(GameContext context, PropertySpace property) {
    this.context = context;
    this.property = property;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Token token = context.getPlayerToken(player);
    Gameboard gameboard = context.getGameboard();
    Space space = getSpace(token, gameboard);
    if (space instanceof ResidentialSpace property) {
      if (!player.equals(property.getOwner())) {
        throw new PlayerActionException("This property <" + property.getName() + "> is not owned by the player <" + player.getName() + ">");
      }

      if (property.getHouseQty() >= context.getConfig().getMaxNumOfApartments()) {
        throw new PlayerActionException(
            "You cannot have more than <" + context.getConfig().getMaxNumOfApartments() + "> apartments");
      }

      if (playerCanAffordApartment(player, property)) {
        try {
          player.pay(property.getValue());
        } catch (NotEnoughMoneyException e) {
          throw new PlayerActionException(e);
        }
        property.addHouse();
      }
    }

    return Optional.empty();
  }

  private Space getSpace(Token token, Gameboard gameboard) {
    if (property != null) {
      return property;
    } else {
      return gameboard.getSpace(token);
    }
  }

  @Override
  public boolean isValidAction(Player player) {
    Token token = context.getPlayerToken(player);
    Gameboard gameboard = context.getGameboard();
    Space space = gameboard.getSpace(token);
    if (space instanceof ResidentialSpace) {
      ResidentialSpace property = (ResidentialSpace) space;

      if (!player.equals(property.getOwner())) {
        return false;
      }

      if (property.getHouseQty() >= context.getConfig().getMaxNumOfApartments()) {
        return false;
      }

      if (playerCanAffordApartment(player, property)) {
        return true;
      }
    }
    return false;
  }

  private boolean playerCanAffordApartment(Player player, ResidentialSpace property) {
    return player.getBalance() - property.getHouseValue() >= 0;
  }

  @Override
  public String getName() {
    return "Buy House";
  }

  @Override
  public String toString() {
    return getName();
  }
}
