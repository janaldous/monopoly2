package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

import java.util.*;

public class GoSpace extends Space
{
    public GoSpace(PlayerActionFactory factory) {
        super("Go", Arrays.asList(factory.createPlayerAction("CollectSalary")));
    }
}
