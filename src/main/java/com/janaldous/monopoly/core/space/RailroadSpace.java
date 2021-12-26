package com.janaldous.monopoly.core.space;
import com.janaldous.monopoly.core.ColorGroup;
import com.janaldous.monopoly.core.playeraction.PlayerAction;

import java.util.*;

public class RailroadSpace extends PropertySpace
{
    public RailroadSpace(String name, int value, int siteOnlyRent, List<PlayerAction> playerActions)
    {
        super(name, value, ColorGroup.RAILROAD, siteOnlyRent, playerActions);
    }
}
