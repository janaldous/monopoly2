package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Gameboard;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.Token;
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
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
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
