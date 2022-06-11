package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerAction;
import lombok.Builder;

import java.util.*;

public class ResidentialSpace extends PropertySpace {
  // implement as priority list? or separate apartments from hotel?
  private int houses;
  private int hotels;
  private boolean isMortgaged;
  private final int housePrice;
  private final int hotelPrice;

  private final int houseRent;
  private final int hotelRent;
  private boolean hasHotel;

  @Builder
  public ResidentialSpace(
      String name,
      int value,
      PropertyGroup colorGroup,
      int housePrice,
      int hotelPrice,
      int siteOnlyRent,
      int houseRent,
      int hotelRent,
      List<PlayerAction> requiredActions,
      List<PlayerAction> playerActions) {
    super(name, value, colorGroup, siteOnlyRent, requiredActions, playerActions);
    this.housePrice = housePrice;
    this.hotelPrice = hotelPrice;
    this.houseRent = houseRent;
    this.hotelRent = hotelRent;
  }

  public void addHouse() {
    houses++;
  }

  public void removeHouse() {
    if (houses - 1 >= 0) {
      houses--;
    }
  }

  public int getHouseQty() {
    return houses;
  }

  public int getHouseValue() {
    return housePrice;
  }

  public void mortgage() {
    isMortgaged = true;
  }

  public int getSiteOnlyRent() {
    return siteOnlyRent;
  }

  public int getHouseRent() {
    return houses * houseRent;
  }

  public int getHotelRent() {
    return hotels * hotelRent;
  }

  public int getHotelQty() {
    return hotels;
  }
}
