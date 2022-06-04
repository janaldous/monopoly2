package com.janaldous.monopoly.core.cardutil;

import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.playeractionutil.TestPlayerAction;

public class TestCard extends Card {
    public TestCard() {
        super("Test Card", new TestPlayerAction());
    }
}
