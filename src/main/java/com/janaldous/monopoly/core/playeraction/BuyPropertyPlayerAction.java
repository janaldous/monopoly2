package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.exception.*;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.space.*;
import com.janaldous.monopoly.core.space.rentstrategy.*;

import java.util.*;

public class BuyPropertyPlayerAction implements PlayerAction {
  private final GameContext context;

  public BuyPropertyPlayerAction(GameContext context) {
    this.context = context;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Gameboard gameboard = context.getGameboard();
    PropertySpace property = (PropertySpace) context.getPlayerSpace(player);
    if (!isValidAction(player)) {
      throw new PlayerActionException("Invalid action");
    }
    try {
      context.getBank().playerToPay(player, property.getValue());
    } catch (NotEnoughMoneyException e) {
      throw new PlayerActionException(e);
    }
    property.setOwner(player);
    player.addProperty(property);

    setRentStrategyForProperty(player, gameboard, property);

    return Optional.empty();
  }

  private void setRentStrategyForProperty(Player player, Gameboard gameboard, PropertySpace property) {
    if (property instanceof ResidentialSpace residence) {
      int playerPropertiesInPropertyGroup =
          Optional.ofNullable(
                  player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
              .map(List::size)
              .orElse(0);
      if (playerPropertiesInPropertyGroup
          == gameboard.getPropertySetSize(property.getPropertyGroup())) {
        gameboard
            .getProperties()
            .get(property.getPropertyGroup())
            .forEach(p -> p.setStrategy(new ResidentialPropertyGroupRentStrategy(residence)));
      } else {
        property.setStrategy(new NormalResidentialRentStrategy(residence));
      }
    }

    if (property instanceof UtilityCompanySpace) {
      int playerPropertiesInPropertyGroup =
          Optional.ofNullable(
                  player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
              .map(List::size)
              .orElse(0);
      if (playerPropertiesInPropertyGroup
          == gameboard.getPropertySetSize(property.getPropertyGroup())) {
        gameboard
            .getProperties()
            .get(property.getPropertyGroup())
            .forEach(p -> p.setStrategy(new UtilitySetGroupRentStrategy(context)));
      } else {
        property.setStrategy(new NormalUtilityRentStrategy(context));
      }
    }

    if (property instanceof RailroadSpace railroad) {
      int playerPropertiesInPropertyGroup =
          Optional.ofNullable(
                  player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
              .map(List::size)
              .orElse(0);
      player
          .getPropertiesByPropertyGroup()
          .get(property.getPropertyGroup())
          .forEach(
              p ->
                  p.setStrategy(
                      new RailroadRentStrategy(playerPropertiesInPropertyGroup, railroad)));
    }
  }

  @Override
  public boolean isValidAction(Player player) {
    Space space = context.getPlayerSpace(player);
    if (space instanceof PropertySpace property) {
      if (hasOwner(property)) {
        return false;
      }

      if (!playerCanAfford(player, property)) {
        return false;
      }
    }
    return true;
  }

  private boolean playerCanAfford(Player player, PropertySpace property) {
    return player.getBalance() - property.getValue() >= 0;
  }

  private boolean hasOwner(PropertySpace property) {
    return property.getOwner() != null;
  }

  @Override
  public String getName() {
    return "Buy Property";
  }

  @Override
  public String toString() {
    return getName();
  }
}
