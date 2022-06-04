package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.exception.*;

import java.util.*;

public class PickCommunityChestCardAction implements PlayerAction
{
    private final GameContext context;
    
    public PickCommunityChestCardAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        Card card = context.getGameboard().takeCommunityChestCard();
        
        return Optional.of(card.getPlayerAction());
    }
    
    @Override
    public String getName() {
        return "";
    }
}
