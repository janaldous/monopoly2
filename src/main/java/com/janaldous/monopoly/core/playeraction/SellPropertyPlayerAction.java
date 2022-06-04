package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.exception.*;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.rentstrategy.NormalResidentialRentStrategy;
import com.janaldous.monopoly.core.token.Token;

import java.util.*;

public class SellPropertyPlayerAction implements PlayerAction
{
    private final GameContext context;
    
    public SellPropertyPlayerAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace) {
            PropertySpace property = (PropertySpace) space;
            
            if (!player.equals(property.getOwner())) {
                return Optional.empty();
            }
            
            boolean beforeHasPropertyGroup = beforeHasPropertyGroup(player, property);
            player.addMoney(property.getValue());
            property.removeOwner();
            player.removeProperty(property);
            
            if (property instanceof ResidentialSpace) {
                ResidentialSpace residence = (ResidentialSpace) property;
                if (beforeHasPropertyGroup) {
                    gameboard.getProperties().get(property.getPropertyGroup())
                            .forEach(p -> p.setStrategy(new NormalResidentialRentStrategy(residence)));
                }
            }
        }
        
        return Optional.empty();
    }
    
    @Override
    public boolean isValidAction(PlayerImpl player) {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace) {
            PropertySpace property = (PropertySpace) space;
            
            if (!player.equals(property.getOwner())) {
                return false;
            }
            
            return true;
        }
        return false;
    }
    
    private boolean beforeHasPropertyGroup(PlayerImpl player, PropertySpace property) {
        Gameboard gameboard = context.getGameboard();
        int playerOwnedPropertiesInGroup = Optional.ofNullable(player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
            .map(List::size)
            .orElse(0);
        return playerOwnedPropertiesInGroup == gameboard.getProperties().get(property.getPropertyGroup()).size();
    }
    
    @Override
    public String getName() {
        return "Sell Property";
    }
}
