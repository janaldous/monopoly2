package com.janaldous.monopoly.core.gameboard;

import com.janaldous.monopoly.core.PropertyGroup;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.space.JailSpace;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import lombok.NonNull;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

public class GameboardImpl implements Gameboard {
  private final Space[] spaces;
  private int jailIndex = -1;
  private final Map<String, Space> spaceNameToSpace;
  private final Map<Token, Integer> tokenPositions;
  private final Queue<Card> communityChestCards;
  private final Queue<Card> chanceCards;
  private final Map<PropertyGroup, List<PropertySpace>> propertyGroupToProperties;

  public GameboardImpl(
          Space[] spaces,
          Map<Token, Integer> tokenPositions,
          Queue<Card> communityChestCards,
          Queue<Card> chanceCards) {
    if (spaces.length == 0) {
      throw new IllegalArgumentException("spaces must have at least length >= 1");
    }

    this.spaces = spaces;
    this.tokenPositions = tokenPositions;
    this.communityChestCards = communityChestCards;
    this.chanceCards = chanceCards;
    spaceNameToSpace =
        Arrays.stream(spaces)
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Space::getName, Function.identity()));
    propertyGroupToProperties = new HashMap<>();
    for (int i = 0; i < spaces.length; i++) {
      Space space = spaces[i];
      if (space instanceof PropertySpace) {
        PropertySpace propertySpace = (PropertySpace) space;
        propertyGroupToProperties.computeIfAbsent(
            propertySpace.getPropertyGroup(), k -> new ArrayList<>());
        propertyGroupToProperties.computeIfPresent(
            propertySpace.getPropertyGroup(),
            (k, v) -> {
              v.add(propertySpace);
              return v;
            });
      }

      if (space instanceof JailSpace) {
        jailIndex = i;
      }
    }
  }

  @Override
  public Card takeChanceCard() {
    return chanceCards.poll();
  }

  @Override
  public Card takeCommunityChestCard() {
    return communityChestCards.poll();
  }

  @Override
  public Space moveBySteps(@NonNull Token token, int steps) {
    validateToken(token);

    int startPosition = getPosition(token);
    int moduloSteps = steps % spaces.length;
    int newPosition =
        steps > 0
            ? calculateForwardMove(startPosition, moduloSteps)
            : calculateBackwardMove(startPosition, moduloSteps);

    return moveToPosition(token, newPosition);
  }

  @Override
  public Space moveToPosition(Token token, int newPosition) {
    tokenPositions.put(token, newPosition);
    return spaces[newPosition];
  }

  private void validateToken(Token token) {
    if (!tokenPositions.containsKey(token))
      throw new IllegalArgumentException("Token does is not in the gameboard");
  }

  private int calculateForwardMove(int startPosition, int moduloSteps) {
    if (moduloSteps == 0) return startPosition;
    return startPosition + moduloSteps;
  }

  private int calculateBackwardMove(int startPosition, int moduloSteps) {
    if (moduloSteps == 0) return startPosition;
    if (startPosition - moduloSteps >= 0) {
      return startPosition - moduloSteps;
    } else {
      return spaces.length - startPosition - moduloSteps;
    }
  }

  @Override
  public Space getSpace(Token token) {
    return spaces[getPosition(token)];
  }

  @Override
  public int getPosition(@NonNull Token token) {
    return tokenPositions.get(token);
  }

  @Override
  public int getPositionBySpaceName(String spaceName) {
    Space space = spaceNameToSpace.get(spaceName);

    for (int i = 0; i < spaces.length; i++) {
      Space s = spaces[i];
      if (space == s) {
        return i;
      }
    }

    return -1;
  }

  @Override
  public Space getSpaceBySpaceName(String spaceName) {
    return spaceNameToSpace.get(spaceName);
  }

  @Override
  public int getPropertySetSize(PropertyGroup colorGroup) {
    return propertyGroupToProperties.get(colorGroup).size();
  }

  @Override
  public Map<PropertyGroup, List<PropertySpace>> getProperties() {
    return propertyGroupToProperties;
  }

  @Override
  public boolean isInJail(Token token) {
    return getPosition(token) == jailIndex;
  }

  @Override
  public void moveToJail(@NonNull Token token) {
    if (jailIndex == -1) {
      throw new IllegalStateException("Jail is not in the board");
    }
    validateToken(token);

    tokenPositions.put(token, jailIndex);
    ((JailSpace) spaces[jailIndex]).jail(token);
  }

  @Override
  public int getNoOfSpaces() {
    return spaces.length;
  }

  @Override
  public void setFreeFromJail(Token token) {
    ((JailSpace) spaces[jailIndex]).setFree(token);
  }
}
