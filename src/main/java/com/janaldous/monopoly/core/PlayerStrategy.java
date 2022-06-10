package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.playeraction.PlayerAction;

public interface PlayerStrategy {
    boolean shouldAct(PlayerAction playerAction);
}
