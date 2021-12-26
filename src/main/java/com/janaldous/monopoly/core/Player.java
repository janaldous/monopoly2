package com.janaldous.monopoly.core;

import com.janaldous.monopoly.core.exception.*;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.*;

public class Player
{
    private final String name;
    private int balance;
    private final Map<ColorGroup, List<PropertySpace>> properties;

    public Player(String name, int startingBalance)
    {
        this.name = name;
        this.balance = startingBalance;
        properties = new HashMap<>();
    }

    public int addMoney(int amount) {
        balance += amount;
        
        System.out.println("player=" + name + " +" + amount + " balance=" + balance);
        
        return balance;
    }

    public int pay(int amount) throws NotEnoughMoneyException {
        if (balance - amount < 0) {
            throw new NotEnoughMoneyException();
        }

        balance -= amount;
        
        System.out.println("player=" + name + " -" + amount + " balance=" + balance);

        return balance;
    }

    public int getBalance() {
        return balance;
    }

    public Map<ColorGroup, List<PropertySpace>> getProperties() {
        return properties;
    }

    public void addProperty(final PropertySpace propertySpace) {
        properties.computeIfAbsent(propertySpace.getPropertyGroup(), k -> new ArrayList<>());
        properties.computeIfPresent(propertySpace.getPropertyGroup(), (k, v) -> {
                v.add(propertySpace);
                return v;
            });
        
    }
    
    public void removeProperty(final PropertySpace property) {
        properties.computeIfPresent(property.getPropertyGroup(), (k, v) -> {
                v.remove(property);
                return v;
            });
    }
}
