package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.PropertyGroup;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.rentstrategy.RentStrategy;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropertySpace extends Space {
  protected Player owner;
  protected final int value;
  protected final List<PlayerAction> playerActionOptions;
  protected final int siteOnlyRent;
  protected RentStrategy strategy;
  protected final PropertyGroup colorGroup;

  protected PropertySpace(
      String name,
      int value,
      PropertyGroup colorGroup,
      int siteOnlyRent,
      List<PlayerAction> requiredActions,
      List<PlayerAction> playerActionOptions) {
    super(name, requiredActions);
    this.colorGroup = colorGroup;
    this.value = value;
    this.siteOnlyRent = siteOnlyRent;
    this.playerActionOptions = playerActionOptions;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public Player getOwner() {
    return owner;
  }

  public void removeOwner() {
    owner = null;
  }

  public int getValue() {
    return value;
  }

  public void setStrategy(RentStrategy strategy) {
    this.strategy = strategy;
  }

  public RentStrategy getStrategy() {
    return strategy;
  }

  public int getRent() {
    if (strategy == null) throw new IllegalStateException("strategy not initialized");
    return strategy.calculateRent();
  }

  @Override
  public Map<String, PlayerAction> getPlayerOptions(final Player player) {
    return playerActionOptions.stream()
        .filter(pa -> pa.isValidAction(player))
        .collect(Collectors.toMap(PlayerAction::getName, Function.identity()));
  }

  public PropertyGroup getPropertyGroup() {
    return colorGroup;
  }

  public boolean hasOwner() {
    return owner != null;
  }
}
