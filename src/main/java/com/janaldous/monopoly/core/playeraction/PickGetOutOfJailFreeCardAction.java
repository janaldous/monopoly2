package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class PickGetOutOfJailFreeCardAction implements PlayerAction {

    public PickGetOutOfJailFreeCardAction() {
    }

    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        player.addGetOutOfJailFree();
        return Optional.empty();
    }

    @Override
    public String getName() {
        return null;
    }
}
