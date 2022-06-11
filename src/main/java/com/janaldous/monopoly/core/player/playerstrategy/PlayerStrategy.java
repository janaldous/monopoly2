package com.janaldous.monopoly.core.player.playerstrategy;

import com.janaldous.monopoly.core.playeraction.PlayerAction;

public interface PlayerStrategy {
    boolean shouldAct(PlayerAction playerAction);
}
