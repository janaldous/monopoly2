package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class GoToJailPlayerAction implements PlayerAction {
    private GameContext context;

    public GoToJailPlayerAction(GameContext context) {
        this.context = context;
    }

    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        context.getGameboard().moveToJail(token);

        return Optional.empty();
    }

    @Override
    public String getName() {
        return null;
    }
}
