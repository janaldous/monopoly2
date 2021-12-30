package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.core.Bank;
import com.janaldous.monopoly.core.BankImpl;
import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.GameContextImpl;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.versions.original.OriginalCardFactory;
import org.junit.jupiter.api.Test;

class OriginalCardFactoryTest {

    @Test
    public void testCreateCommunityCardDeck() {
        Bank bank = new BankImpl();
        GameContext context = new GameContextImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, context);
        OriginalCardFactory originalCardFactory = new OriginalCardFactory(playerActionFactory);

        originalCardFactory.createCommunityChestCardDeck();
    }

    @Test
    public void testCreateChanceCardDeck() {
        Bank bank = new BankImpl();
        GameContext context = new GameContextImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, context);
        OriginalCardFactory originalCardFactory = new OriginalCardFactory(playerActionFactory);

        originalCardFactory.createChanceCardDeck();
    }

}