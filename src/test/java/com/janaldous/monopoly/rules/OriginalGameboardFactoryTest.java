package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.gamecontext.GameContextImpl;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
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