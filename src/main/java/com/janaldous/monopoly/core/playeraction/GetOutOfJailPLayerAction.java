package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class GetOutOfJailPLayerAction implements PlayerAction {
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        player.useGetOutOfJailFree();
        return Optional.empty();
    }

    @Override
    public String getName() {
        return null;
    }
}
