package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.core.space.Space;
import java.util.Map;


public class SpaceActionRegistry
{
    private final Map<Class<Space>, SpaceActionHandler> registry;
    
    public SpaceActionRegistry(Map<Class<Space>, SpaceActionHandler> registry) {
        this.registry = registry;
    }
    
    public SpaceActionHandler getHandler(Class<Space> clazz) {
        return registry.get(clazz);
    }
    
}
