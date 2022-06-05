package com.janaldous.monopoly.core.gameboard;

import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.cardutil.TestCard;
import com.janaldous.monopoly.core.cardutil.TestToken;
import com.janaldous.monopoly.core.space.DoNothingSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameboardImplTest {

    Queue<Card> communityChestCards;

    Queue<Card> chanceCards;

    // immutable inputs
    Space[] spaces =
            new Space[] {
                    new DoNothingSpace("space1"), new DoNothingSpace("space2"), new DoNothingSpace("space3")
            };
    TestToken testToken = new TestToken("token1");
    Card testCard = new TestCard();

    // mutable inputs
    Map<Token, Integer> tokenPositions;

    @BeforeEach
    void beforeEach() {
        tokenPositions =
                new HashMap<>() {
                    {
                        put(testToken, 0);
                    }
                };

        communityChestCards = null;
        chanceCards = null;
    }

    @Test
    void takeChanceCard_noMoreCards() {
        // given empty chance card
        Queue<Card> chanceCards = new LinkedList<>();
        Gameboard gameboard =
                new GameboardImpl(spaces, tokenPositions, mock(Queue.class), chanceCards);

        // when
        Card card = gameboard.takeChanceCard();

        // then
        assertNull(card);
    }

    @Test
    void takeChanceCard() {
        // given empty chance card
        Queue<Card> chanceCards = new LinkedList<>() {{
            add(testCard);
        }};
        Gameboard gameboard =
                new GameboardImpl(spaces, tokenPositions, mock(Queue.class), chanceCards);

        // when
        Card resultCard = gameboard.takeChanceCard();

        // then
        assertEquals(testCard, resultCard);
    }

    @Test
    void takeCommunityChestCard_noMoreCards() {
        // given empty chance card
        Queue<Card> communityChestCards = new LinkedList<>();
        Gameboard gameboard =
                new GameboardImpl(spaces, tokenPositions, communityChestCards, mock(Queue.class));

        // when
        Card card = gameboard.takeChanceCard();

        // then
        assertNull(card);
    }

    @Test
    void takeCommunityChestCard() {
        // given empty chance card
        Queue<Card> communityChestCards = new LinkedList<>() {{
            add(testCard);
        }};
        Gameboard gameboard =
                new GameboardImpl(spaces, tokenPositions, communityChestCards, mock(Queue.class));

        // when
        Card resultCard = gameboard.takeCommunityChestCard();

        // then
        assertEquals(testCard, resultCard);
    }
}