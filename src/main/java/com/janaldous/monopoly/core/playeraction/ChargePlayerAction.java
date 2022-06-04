package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.exception.*;
import java.util.*;

public class ChargePlayerAction implements PlayerAction
{
    private final int cost;
    private final Bank bank;
    
    public ChargePlayerAction(int cost, Bank bank) {
        this.cost = cost;
        this.bank = bank;
    }
    
    @Override
    public Optional<PlayerAction> act(PlayerImpl player) throws PlayerActionException {
        try
        {
            player.pay(cost);
            bank.deposit(cost);
        }
        catch (NotEnoughMoneyException e)
        {
            throw new PlayerActionException(e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}
