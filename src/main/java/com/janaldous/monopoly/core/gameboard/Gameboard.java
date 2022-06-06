package com.janaldous.monopoly.core.gameboard;
import com.janaldous.monopoly.core.PropertyGroup;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;

import java.util.*;


public interface Gameboard {
    Space moveBySteps(Token token, int steps);
    Space moveToPosition(Token token, int position);
    Card takeCommunityChestCard();
    Card takeChanceCard();
    int getPosition(Token token);
    Space getSpace(Token token);
    int getPositionBySpaceName(String spaceName);
    Space getSpaceBySpaceName(String spaceName);
    int getPropertySetSize(PropertyGroup colorGroup);
    Map<PropertyGroup, List<PropertySpace>> getProperties();
    boolean isInJail(Token token);
    void moveToJail(Token token);
    void setFreeFromJail(Token token);

    int getNoOfSpaces();
}
