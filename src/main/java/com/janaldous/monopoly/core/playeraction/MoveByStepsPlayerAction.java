package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Gameboard;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.Token;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class MoveByStepsPlayerAction implements PlayerAction
{
    private final GameContext context;
    private int steps;

    public MoveByStepsPlayerAction(int steps, GameContext context) {
        this.steps = steps;
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        gameboard.move(token, steps);
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "Move player by steps: " + steps;
    }
}
