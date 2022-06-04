package com.janaldous.monopoly.core.playeractionutil;

import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.playeraction.PlayerAction;

import java.util.Optional;

public class TestPlayerAction implements PlayerAction {
    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "test player action";
    }
}
