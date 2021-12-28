package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.GameContextImpl;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.SpaceFactory;
import org.junit.jupiter.api.Test;

class OriginalGameboardFactoryTest {

    @Test
    void test() {
        Bank bank = new BankImpl();
        GameContext context = new GameContextImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, context);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        OriginalGameboardFactory factory = new OriginalGameboardFactory(spaceFactory);

        factory.createSpaces();
    }

}