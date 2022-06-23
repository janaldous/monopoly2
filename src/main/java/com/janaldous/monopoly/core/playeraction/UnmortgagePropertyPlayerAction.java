package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.bank.Bank;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.List;
import java.util.Optional;

public class UnmortgagePropertyPlayerAction implements PlayerAction {

    private final GameContext context;
    private final PropertySpace propertyToUnmortgage;
    private final Bank bank;

    public UnmortgagePropertyPlayerAction(GameContext context,
                                          PropertySpace propertyToUnmortgage,
                                          Bank bank) {
        this.context = context;
        this.propertyToUnmortgage = propertyToUnmortgage;
        this.bank = bank;
    }

    @Override
    public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
        try {
            bank.playerToPay(player, (int) (propertyToUnmortgage.getMortgageValue() * 1.1));
            propertyToUnmortgage.unmortgage();
        } catch (NotEnoughMoneyException e) {
            throw new PlayerActionException(e);
        }

        return Optional.empty();
    }

    @Override
    public boolean isValidAction(Player player) {
        boolean playerOwnsProperty = player.equals(propertyToUnmortgage.getOwner());
        if (playerOwnsProperty) {
            return propertyToUnmortgage.isMortgaged();
        }
        return false;
    }
    
    @Override
    public String getName() {
        return "Unmortgage Property <" + propertyToUnmortgage.getName() + ">";
    }

    @Override
    public String toString() {
        return getName();
    }
}
