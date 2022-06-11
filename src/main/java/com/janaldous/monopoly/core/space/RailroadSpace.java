package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerAction;

import java.util.*;

public class RailroadSpace extends PropertySpace {
  private Map<Integer, Integer> noOfPropertyToRent;

  public RailroadSpace(
      String name,
      int value,
      Map<Integer, Integer> noOfPropertyToRent,
      List<PlayerAction> requiredActions,
      List<PlayerAction> playerActions) {
    super(name, value, PropertyGroup.RAILROAD, 0, requiredActions, playerActions);
    this.noOfPropertyToRent = noOfPropertyToRent;
  }

  public int getRailroadRent(int noOfProperties) {
    return noOfPropertyToRent.get(noOfProperties);
  }
}
