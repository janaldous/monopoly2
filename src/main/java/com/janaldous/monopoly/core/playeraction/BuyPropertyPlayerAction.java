package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.exception.*;
import com.janaldous.monopoly.core.space.*;
import com.janaldous.monopoly.core.space.rentstrategy.*;

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

                    int playerPropertiesInPropertyGroup = Optional.ofNullable(player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
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
                    int playerPropertiesInPropertyGroup = Optional.ofNullable(player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
                            .map(List::size)
                            .orElse(0);
                    if (playerPropertiesInPropertyGroup == gameboard.getPropertySetSize(property.getPropertyGroup())) {
                        gameboard.getProperties().get(property.getPropertyGroup())
                                .forEach(p -> p.setStrategy(new UtilitySetGroupRentStrategy(context)));
                    } else {
                        property.setStrategy(new NormalUtilityRentStrategy(context));
                    }
                }

                if (property instanceof RailroadSpace) {
                    RailroadSpace railroad = (RailroadSpace) property;
                    int playerPropertiesInPropertyGroup = Optional.ofNullable(player.getPropertiesByPropertyGroup().get(property.getPropertyGroup()))
                            .map(List::size)
                            .orElse(0);
                    player.getPropertiesByPropertyGroup().get(property.getPropertyGroup())
                            .forEach(p -> p.setStrategy(new RailroadRentStrategy(playerPropertiesInPropertyGroup, railroad)));
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
