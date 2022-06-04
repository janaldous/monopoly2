package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerImpl implements Player {
  private final String name;
  private int balance;
  private final Map<PropertyGroup, List<PropertySpace>> properties;
  private int getOutOfJailFreeCards;

  public PlayerImpl(String name, int startingBalance) {
    this.name = name;
    this.balance = startingBalance;
    properties = new HashMap<>();
  }

  @Override
  public int addMoney(int amount) {
    balance += amount;

    System.out.println("player=" + name + " +" + amount + " balance=" + balance);

    return balance;
  }

  @Override
  public int pay(int amount) throws NotEnoughMoneyException {
    if (balance - amount < 0) {
      throw new NotEnoughMoneyException();
    }

    balance -= amount;

    System.out.println("player=" + name + " -" + amount + " balance=" + balance);

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
}
