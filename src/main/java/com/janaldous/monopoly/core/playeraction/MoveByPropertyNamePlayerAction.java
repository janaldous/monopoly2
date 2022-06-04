package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.core.exception.*;
import java.util.*;

public class MoveByPropertyNamePlayerAction implements PlayerAction
{
    private final String propertyName;
    private final GameContext context;

    public MoveByPropertyNamePlayerAction(String propertyName, GameContext context) {
        this.propertyName = propertyName;
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        int startPosition = gameboard.getPosition(token);
        int endPosition = gameboard.getPositionBySpaceName(propertyName);
        gameboard.move(token, Math.abs(endPosition - startPosition));
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}
