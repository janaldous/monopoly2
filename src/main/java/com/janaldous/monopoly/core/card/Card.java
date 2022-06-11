package com.janaldous.monopoly.core.card;

import com.janaldous.monopoly.core.playeraction.PlayerAction;

public class Card {
  private final PlayerAction playerAction;
  private final String name;

  protected Card(String name, PlayerAction playerAction) {
    this.name = name;
    this.playerAction = playerAction;
  }

  public PlayerAction getPlayerAction() {
    return playerAction;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
