package com.janaldous.monopoly.core.card;

import com.janaldous.monopoly.core.playeraction.PlayerAction;

public class Card
{
    private final PlayerAction playerAction;
    
    protected Card(PlayerAction playerAction)
    {
        this.playerAction = playerAction;
    }
    
    public PlayerAction getPlayerAction() {
        return playerAction;
    }
}
