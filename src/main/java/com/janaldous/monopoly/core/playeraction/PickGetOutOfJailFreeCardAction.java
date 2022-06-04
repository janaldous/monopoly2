package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class PickGetOutOfJailFreeCardAction implements PlayerAction {

    public PickGetOutOfJailFreeCardAction() {
    }

    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        player.addGetOutOfJailFree();
        return Optional.empty();
    }

    @Override
    public String getName() {
        return null;
    }
}
