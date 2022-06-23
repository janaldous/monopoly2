package com.janaldous.monopoly.core.bank;

import com.janaldous.monopoly.core.gameboard.GameboardImpl;
import com.janaldous.monopoly.core.space.PropertyGroup;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MortgageEligibilityChecker {

  private final GameboardImpl gameboard;

  public MortgageEligibilityChecker(GameboardImpl gameboard) {
    this.gameboard = gameboard;
  }

  public List<PropertySpace> getEligibleProperties(
      Map<PropertyGroup, List<PropertySpace>> groupedProperties) {

    Set<PropertyGroup> fullGroups =
        groupedProperties.entrySet().stream()
            .filter(
                (entry) -> entry.getValue().size() == gameboard.getPropertySetSize(entry.getKey()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());

    return groupedProperties.entrySet().stream()
            .map(Map.Entry::getValue)
            .flatMap(List::stream)
            .filter(x -> x instanceof PropertySpace)
            .filter(x -> !x.isMortgaged())
            .filter(x -> isPropertyNotImproved(x, fullGroups))
            .collect(Collectors.toList());
  }

  private boolean isPropertyNotImproved(PropertySpace property, Set<PropertyGroup> fullGroups) {
    if (property instanceof ResidentialSpace residentialSpace) {
      boolean propertyIsPartOfGroup = fullGroups.contains(residentialSpace.getPropertyGroup());
      boolean propertyHasBuildings = residentialSpace.getHouseQty() > 0;
      return propertyIsPartOfGroup && propertyHasBuildings;
    }
    return true;
  }

}
