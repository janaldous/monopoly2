package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

import java.util.*;

public class PickCardSpace extends Space
{
    public PickCardSpace(PlayerActionFactory factory)
    {
        super("Community Chest", Arrays.asList(factory.createPlayerAction("PickCommunityChestCard")));
    }
}
