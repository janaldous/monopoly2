package com.janaldous.monopoly.core.card;

import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

public class CardFactory
{
    private final PlayerActionFactory factory;
    
    public CardFactory(PlayerActionFactory factory)
    {
        this.factory = factory;
    }
    
    public Card createCommunityChestCard(String name) {
        switch (name) {
            case "AdvanceToStCharlesPlace":
                return new CommunityChestCard(factory.createMovePlayerAction("St Charles Place"));
            default:
                throw new IllegalArgumentException("Cannot recognize action " + name);
        }
    }
}
