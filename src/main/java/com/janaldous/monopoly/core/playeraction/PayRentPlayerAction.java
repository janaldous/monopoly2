package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;

import java.util.Optional;

public class PayRentPlayerAction implements PlayerAction
{
    private static final int MAX_NUMBER_OF_APARTMENTS = 4;
    
    private final GameContext context;
    
    public PayRentPlayerAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace) {
            PropertySpace property = (PropertySpace) space;
            int rent = property.getRent();
            PlayerImpl owner = property.getOwner();
            
            // owner does not pay rent
            if (owner.equals(player)) {
                return Optional.empty();
            }
            
            if (rent > 0) {
                try
                {
                    player.pay(rent);
                }
                catch (NotEnoughMoneyException neme)
                {
                    throw new PlayerActionException(neme);
                }
                owner.addMoney(rent);
            }
        }
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}
