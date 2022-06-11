package com.janaldous.monopoly.core.player;

import com.janaldous.monopoly.core.space.PropertyGroup;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.player.playerstrategy.PlayerStrategy;
import com.janaldous.monopoly.core.player.sellstrategy.SellPropertyStrategy;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Player {

  String getName();

  int addMoney(int amount);

  int pay(int amount) throws NotEnoughMoneyException;

  int getBalance();

  Map<PropertyGroup, List<PropertySpace>> getPropertiesByPropertyGroup();

  List<PropertySpace> getProperties();

  void addProperty(PropertySpace propertySpace);

  void removeProperty(PropertySpace property);

  void addGetOutOfJailFree();

  void useGetOutOfJailFree();

  void setStrategy(PlayerStrategy strategy);

  boolean shouldAct(PlayerAction playerAction);

  void setSellStrategy(SellPropertyStrategy sellStrategy);

  /**
   * Calculate which property to sell in case
   * @return
   */
  Optional<PropertySpace> getPropertyToSell();

  List<PlayerAction> getActionOptions();
}
