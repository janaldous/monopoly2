package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerAction;

import java.util.List;

public class UtilityCompanySpace extends PropertySpace {
  public UtilityCompanySpace(
          String name,
          int value,
          List<PlayerAction> requiredActions,
          List<PlayerAction> playerActions,
          int mortgageValue) {
    super(name, value, PropertyGroup.UTILITY, 0, requiredActions, playerActions, mortgageValue);
  }
}
