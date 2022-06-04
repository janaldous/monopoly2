package com.janaldous.monopoly.core.playeraction;
import com.janaldous.monopoly.core.Bank;

import java.util.Optional;

public class CollectSalaryAction implements PlayerAction
{
    private final Bank bank;
    
    public CollectSalaryAction(Bank bank) {
        this.bank = bank;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) {
        int money = bank.withdraw(200);
        player.addMoney(money);
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}
