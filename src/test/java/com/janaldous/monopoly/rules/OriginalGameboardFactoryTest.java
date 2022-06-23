package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.core.bank.Bank;
import com.janaldous.monopoly.core.bank.BankImpl;
import com.janaldous.monopoly.core.bank.MortgageEligibilityChecker;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.gamecontext.GameContextImpl;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;
import com.janaldous.monopoly.versions.original.OriginalGameboardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OriginalGameboardFactoryTest {

    @Mock
    MortgageEligibilityChecker mortgageEligibilityCheckerMock;

    @Test
    void test() {
        Bank bank = new BankImpl();
        GameContext context = new GameContextImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, context, mortgageEligibilityCheckerMock);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        OriginalGameboardFactory factory = new OriginalGameboardFactory(spaceFactory);

        factory.createSpaces();
    }

}