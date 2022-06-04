package com.janaldous.monopoly.core.gameboard;

import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.cardutil.TestToken;
import com.janaldous.monopoly.core.space.DoNothingSpace;
import com.janaldous.monopoly.core.space.JailSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameboardImplTest_MoveToJail {

  @Mock Queue<Card> communityChestCardsMock;

  @Mock Queue<Card> chanceCardsMock;

  // immutable inputs
  Space[] spaces =
      new Space[] {
        new DoNothingSpace("space1"),
        new DoNothingSpace("space2"),
        new DoNothingSpace("space3"),
        new JailSpace(),
        new DoNothingSpace("space4")
      };
  TestToken testToken = new TestToken("token1");

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
  }

  @ParameterizedTest
  @MethodSource("provideInputsForMoveTest")
  void move(int initialPosition, int expectedPosition) {
    // given
    tokenPositions.put(testToken, initialPosition);
    Gameboard gameboard =
        new GameboardImpl(spaces, tokenPositions, null, communityChestCardsMock, chanceCardsMock);

    // when
    gameboard.moveToJail(testToken);

    // then
    assertEquals(expectedPosition, gameboard.getPosition(testToken));
  }

  @Test
  void move_nullToken() {
    Gameboard gameboard =
        new GameboardImpl(spaces, tokenPositions, null, communityChestCardsMock, chanceCardsMock);

    // when
    Executable executable = () -> gameboard.moveToJail(null);

    // then
    assertThrows(
        NullPointerException.class, executable, "testToken is marked non-null but is null");
  }

  @Test
  void move_invalidToken() {
    Token invalidToken = new TestToken("testtoken2");
    Gameboard gameboard =
        new GameboardImpl(spaces, tokenPositions, null, communityChestCardsMock, chanceCardsMock);

    // when
    Executable executable = () -> gameboard.moveToJail(invalidToken);

    // then
    assertThrows(
        IllegalArgumentException.class, executable, "testToken is marked non-null but is null");
  }

  private static Stream<Arguments> provideInputsForMoveTest() {
    // stepsToMove, initialPosition, expectedPosition
    return Stream.of(
        Arguments.of(0, 3),
        Arguments.of(1, 3),
        Arguments.of(2, 3),
        Arguments.of(3, 3),
        Arguments.of(4, 3));
  }
}
