package com.janaldous.monopoly.core.playeractionutil;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.playeraction.PlayerAction;

import java.util.List;
import java.util.Optional;

public class TestPlayerAction implements PlayerAction {
    @Override
    public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "test player action";
    }
}
