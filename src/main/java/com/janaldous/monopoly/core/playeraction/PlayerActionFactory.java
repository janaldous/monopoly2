package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.GameContext;

public class PlayerActionFactory
{
    private final Bank bank;
    private final GameContext context;
    
    public PlayerActionFactory(Bank bank, GameContext context)
    {
        this.bank = bank;
        this.context = context;
    }
    
    public PlayerAction createPlayerAction(String actionName) {
        switch (actionName) {
            case "CollectSalary":
                return new CollectSalaryAction(bank);
            case "PickCommunityChestCard":
                return new PickCommunityChestCardAction(context);
            default:
                throw new IllegalArgumentException("Cannot recognize action " + actionName);
        }
    }
    
    public PlayerAction createChargePlayerAction(int amount) {
        return new ChargePlayerAction(amount, bank);
    }
    
    public PlayerAction createMovePlayerAction(String propertyName) {
        return new MovePlayerAction(propertyName, context);
    }
    
    public PlayerAction createBuyPropertyAction() {
        return new BuyPropertyPlayerAction(context);
    }
    
    public PlayerAction createBuyHouseAction() {
        return new BuyHousePlayerAction(context);
    }
    
    public PlayerAction createPayRentAction() {
        return new PayRentPlayerAction(context);
    }
    
    public PlayerAction createSellPropertyAction() {
        return new SellPropertyPlayerAction(context);
    }
}
