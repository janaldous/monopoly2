package com.janaldous.monopoly.controller;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerImplTest {

    @Mock
    Player player1;

    @Mock
    Player player2;

    @Mock
    GameContext gameContextMock;

    GameControllerImpl gameController;

    @BeforeEach
    void beforeEach() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        when(gameContextMock.getPlayers()).thenReturn(players);

        gameController = new GameControllerImpl(gameContextMock);
    }

    @Test
    void testGetCurrentPlayer() {
        // given

        // when
        Player currentPlayer = gameController.getCurrentPlayer();

        // then
        assertEquals(player1, currentPlayer);
    }

    @Test
    void test_finishPlayerTurn_firstPlayerInList() {
        // given
        gameController.setCurrentPlayerIndex(0);
        gameController.finishPlayerTurn();

        // when
        Player currentPlayer = gameController.getCurrentPlayer();

        // then
        assertEquals(player1, currentPlayer);
    }

    @Test
    void test_finishPlayerTurn_lastPlayerInList() {
        // given
        gameController.setCurrentPlayerIndex(1);
        gameController.finishPlayerTurn();

        // when
        Player currentPlayer = gameController.getCurrentPlayer();

        // then
        assertEquals(player2, currentPlayer);
    }

}