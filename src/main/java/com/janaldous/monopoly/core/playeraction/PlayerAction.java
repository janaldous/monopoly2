package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.exception.*;
import java.util.Optional;

public interface PlayerAction
{
    Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException;
    default boolean isValidAction(PlayerImpl player) {
        return true;
    }
    String getName();
}
