package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.player.Player;
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
  protected boolean isMortgaged;
  private final int mortgageValue;

  protected PropertySpace(
          String name,
          int value,
          PropertyGroup colorGroup,
          int siteOnlyRent,
          List<PlayerAction> requiredActions,
          List<PlayerAction> playerActionOptions,
          int mortgageValue) {
    super(name, requiredActions);
    this.colorGroup = colorGroup;
    this.value = value;
    this.siteOnlyRent = siteOnlyRent;
    this.playerActionOptions = playerActionOptions;
    this.mortgageValue = mortgageValue;
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
    // cannot pay rent to mortgaged properties
    if (isMortgaged) return 0;

    if (strategy == null) throw new IllegalStateException("strategy not initialized");
    return strategy.calculateRent();
  }

  @Override
  public Map<String, PlayerAction> getSpaceOptions(final Player player) {
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

  public boolean isMortgaged() {
    return isMortgaged;
  }

  public void mortgage() {
    isMortgaged = true;
  }

  public void unmortgage() {
    isMortgaged = true;
  }

  public int getMortgageValue() {
    return mortgageValue;
  }
}
