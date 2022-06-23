package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.rentstrategy.NormalResidentialRentStrategy;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Optional;

public class SellPropertyPlayerAction implements PlayerAction {

    private final GameContext context;
    private final PropertySpace propertyToSell;

    public SellPropertyPlayerAction(GameContext context, PropertySpace propertyToSell) {
        this.context = context;
        this.propertyToSell = propertyToSell;
    }

    /**
     * If property to sell == null, then use player's current space
     * @param context
     */
    public SellPropertyPlayerAction(GameContext context) {
        this.context = context;
        this.propertyToSell = null;
    }
    
    @Override
    public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
        Gameboard gameboard = context.getGameboard();
        Space space = getSpaceToSell(player);
        if (space instanceof PropertySpace property) {
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

    private Space getSpaceToSell(Player player) {
        if (propertyToSell != null) {
            return propertyToSell;
        } else {
            return context.getPlayerSpace(player);
        }
    }

    @Override
    public boolean isValidAction(Player player) {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace property) {
            return player.equals(property.getOwner());
        }
        return false;
    }
    
    private boolean beforeHasPropertyGroup(Player player, PropertySpace property) {
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

    @Override
    public String toString() {
        return getName();
    }
}
