package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.exception.*;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.UtilityCompanySpace;
import com.janaldous.monopoly.core.space.rentstrategy.NormalResidentialRentStrategy;
import com.janaldous.monopoly.core.space.rentstrategy.NormalUtilityRentStrategy;
import com.janaldous.monopoly.core.space.rentstrategy.ResidentialPropertyGroupRentStrategy;
import com.janaldous.monopoly.core.space.rentstrategy.UtilitySetGroupRentStrategy;

import java.util.*;

public class BuyPropertyPlayerAction implements PlayerAction
{
    private final GameContext context;

    public BuyPropertyPlayerAction(GameContext context) {
        this.context = context;
    }

    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace) {
            PropertySpace property = (PropertySpace) space;

            if (hasOwner(property)) {
                throw new PlayerActionException("Property is already owned by another player");
            }

            if (playerCanAfford(player, property)) {
                try
                {
                    player.pay(property.getValue());
                }
                catch (NotEnoughMoneyException neme)
                {
                    throw new PlayerActionException(neme);
                }
                property.setOwner(player);
                player.addProperty(property);

                if (property instanceof ResidentialSpace) {
                    ResidentialSpace residence = (ResidentialSpace) property;

                    int playerPropertiesInPropertyGroup = Optional.ofNullable(player.getProperties().get(property.getPropertyGroup()))
                        .map(List::size)
                        .orElse(0);
                    if (playerPropertiesInPropertyGroup == gameboard.getPropertySetSize(property.getPropertyGroup())) {
                        gameboard.getProperties().get(property.getPropertyGroup())
                        .forEach(p -> p.setStrategy(new ResidentialPropertyGroupRentStrategy(residence)));
                    } else {
                        property.setStrategy(new NormalResidentialRentStrategy(residence));
                    }
                }
                
                if (property instanceof UtilityCompanySpace) {
                    UtilityCompanySpace utility = (UtilityCompanySpace) property;
                    
                    int playerPropertiesInPropertyGroup = Optional.ofNullable(player.getProperties().get(property.getPropertyGroup()))
                        .map(List::size)
                        .orElse(0);
                    if (playerPropertiesInPropertyGroup == gameboard.getPropertySetSize(property.getPropertyGroup())) {
                        gameboard.getProperties().get(property.getPropertyGroup())
                        .forEach(p -> p.setStrategy(new UtilitySetGroupRentStrategy(context)));
                    } else {
                        property.setStrategy(new NormalUtilityRentStrategy(context));
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isValidAction(Player player) {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace) {
            PropertySpace property = (PropertySpace) space;

            if (hasOwner(property)) {
                return false;
            }
            
            if (!playerCanAfford(player, property)) {
                return false;
            }
        }
        return true;
    }

    private boolean playerCanAfford(Player player, PropertySpace property) {
        return player.getBalance() - property.getValue() >= 0;
    }

    private boolean hasOwner(PropertySpace property) {
        return property.getOwner() != null;
    }

    @Override
    public String getName() {
        return "Buy Property";
    }
}
