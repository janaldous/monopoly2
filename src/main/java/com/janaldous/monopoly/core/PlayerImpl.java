package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.PropertySpace;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.*;
import java.util.stream.Collectors;

@Log
@ToString
public class PlayerImpl implements Player {
  @Getter
  private final String name;
  private int balance;
  private final Map<PropertyGroup, List<PropertySpace>> properties;
  private int getOutOfJailFreeCards;
  private PlayerStrategy strategy;

  public PlayerImpl(String name, int startingBalance, PlayerStrategy strategy) {
    this.name = name;
    this.balance = startingBalance;
    this.strategy = strategy;
    properties = new HashMap<>();
  }

  public PlayerImpl(String name, int startingBalance) {
    this(name, startingBalance, null);
  }

  @Override
  public int addMoney(int amount) {
    balance += amount;

    log.info(name + " +" + amount + " new balance=" + balance);

    return balance;
  }

  @Override
  public int pay(int amount) throws NotEnoughMoneyException {
    if (balance - amount < 0) {
      throw new NotEnoughMoneyException();
    }

    balance -= amount;

    log.info(name + " -" + amount + " balance=" + balance);

    return balance;
  }

  @Override
  public int getBalance() {
    return balance;
  }

  @Override
  public Map<PropertyGroup, List<PropertySpace>> getPropertiesByPropertyGroup() {
    return properties;
  }

  @Override
  public List<PropertySpace> getProperties() {
    return properties.values().stream().flatMap(List::stream).collect(Collectors.toList());
  }

  @Override
  public void addProperty(final PropertySpace propertySpace) {
    properties.computeIfAbsent(propertySpace.getPropertyGroup(), k -> new ArrayList<>());
    properties.computeIfPresent(
        propertySpace.getPropertyGroup(),
        (k, v) -> {
          v.add(propertySpace);
          return v;
        });
  }

  @Override
  public void removeProperty(final PropertySpace property) {
    properties.computeIfPresent(
        property.getPropertyGroup(),
        (k, v) -> {
          v.remove(property);
          return v;
        });
  }

  @Override
  public void addGetOutOfJailFree() {
    getOutOfJailFreeCards++;
  }

  @Override
  public void useGetOutOfJailFree() {
    if (getOutOfJailFreeCards <= 0) throw new IllegalStateException("Cannot remove cards");
    getOutOfJailFreeCards--;
  }

  @Override
  public void setStrategy(PlayerStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public boolean shouldAct(PlayerAction playerAction) {
    return strategy.shouldAct(playerAction);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlayerImpl player = (PlayerImpl) o;
    return name.equals(player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
