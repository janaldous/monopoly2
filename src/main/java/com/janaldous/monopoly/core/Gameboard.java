package com.janaldous.monopoly.core;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.Space;

import java.util.*;


public interface Gameboard {
    Space move(Token token, int steps);
    Card takeCommunityChestCard();
    Card takeChanceCard();
    int getPosition(Token token);
    Space getSpace(Token token);
    int getPositionBySpaceName(String spaceName);
    Space getSpaceBySpaceName(String spaceName);
    int getPropertySetSize(PropertyGroup colorGroup);
    Map<PropertyGroup, List<PropertySpace>> getProperties();
    boolean inJail(Token token);
    void moveToJail(Token token);
    void setFreeFromJail(Token token);

    int getNoOfSpaces();
}
