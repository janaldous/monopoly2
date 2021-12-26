package com.janaldous.monopoly.core.space;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.playeraction.PlayerAction;

import java.util.*;

public class Space
{
    private final String name;
    private final List<PlayerAction> requiredActions;
    
    protected Space(String name, List<PlayerAction> requiredActions) {
        this.name = name;
        this.requiredActions = requiredActions;
    }
    
    protected Space() {
        this.name = null;
        this.requiredActions = null;
    }
    
    public String getName() {
        return name;
    }
    
    public Map<String, PlayerAction> getPlayerOptions(Player player) {
        return Collections.emptyMap();
    }
    
    public List<PlayerAction> getRequiredActions() {
        return requiredActions;
    }
}
