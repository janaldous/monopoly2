package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.List;
import java.util.Map;

public interface Player {
  int addMoney(int amount);

  int pay(int amount) throws NotEnoughMoneyException;

  int getBalance();

  Map<PropertyGroup, List<PropertySpace>> getPropertiesByPropertyGroup();

  List<PropertySpace> getProperties();

  void addProperty(PropertySpace propertySpace);

  void removeProperty(PropertySpace property);

  void addGetOutOfJailFree();

  void useGetOutOfJailFree();
}
