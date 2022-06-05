package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.PropertyGroup;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyPropertyPlayerActionTest {

  @Mock GameContext contextMock;
  @Mock Gameboard gameboardMock;

  ResidentialSpace residentialSpace;
  Map<PropertyGroup, List<PropertySpace>> propertyGroups;

  @BeforeEach
  void beforeEach() {
    residentialSpace =
        ResidentialSpace.builder()
            .name("Test Property")
            .colorGroup(PropertyGroup.PINK)
            .value(200)
            .housePrice(10)
            .hotelPrice(10)
            .siteOnlyRent(10)
            .houseRent(10)
            .hotelRent(10)
            .requiredActions(Collections.emptyList())
            .playerActions(Collections.emptyList())
            .build();
    propertyGroups = new HashMap<>(){{
      put(PropertyGroup.PINK, List.of(residentialSpace, residentialSpace, residentialSpace));
    }};
  }

  @Test
  void testIsValidAction_playerHasSufficientMoney() {
    // given
    Player player = new PlayerImpl("test", 200);

    when(contextMock.getPlayerSpace(player)).thenReturn(residentialSpace);

    BuyPropertyPlayerAction action = new BuyPropertyPlayerAction(contextMock);

    // when
    boolean result = action.isValidAction(player);

    // then
    assertTrue(result);
  }

  @Test
  void testIsValidAction_playerHasInsufficientMoney() {
    // given
    Player player = new PlayerImpl("test", 199);

    when(contextMock.getPlayerSpace(player)).thenReturn(residentialSpace);

    BuyPropertyPlayerAction action = new BuyPropertyPlayerAction(contextMock);

    // when
    boolean result = action.isValidAction(player);

    // then
    assertFalse(result);
  }

  @Test
  void testIsValidAction_spaceAlreadyHasOwner() {
    // given
    Player player = new PlayerImpl("test", 199);
    residentialSpace.setOwner(mock(Player.class));

    when(contextMock.getPlayerSpace(player)).thenReturn(residentialSpace);

    BuyPropertyPlayerAction action = new BuyPropertyPlayerAction(contextMock);

    // when
    boolean result = action.isValidAction(player);

    // then
    assertFalse(result);
  }

  @Test
  void testAct_playerWillOwnResidence() throws PlayerActionException {
    // given
    Player player = new PlayerImpl("test", 200);

    when(contextMock.getPlayerSpace(player)).thenReturn(residentialSpace);
    when(contextMock.getGameboard()).thenReturn(gameboardMock);

    BuyPropertyPlayerAction action = new BuyPropertyPlayerAction(contextMock);

    // when
    Optional<PlayerAction> result = action.act(player);

    // then
    assertEquals(player, residentialSpace.getOwner());
    assertThat(player.getProperties()).contains(residentialSpace);
    assertThat(result.isPresent()).isFalse();
    assertEquals(10, residentialSpace.getRent());
  }

  @Test
  void testAct_playerWillOwnColorSet() throws PlayerActionException {
    // given
    Player player = new PlayerImpl("test", 200);
    player.addProperty(residentialSpace);
    player.addProperty(residentialSpace);

    when(contextMock.getPlayerSpace(player)).thenReturn(residentialSpace);
    when(contextMock.getGameboard()).thenReturn(gameboardMock);
    when(gameboardMock.getPropertySetSize(PropertyGroup.PINK)).thenReturn(3);
    when(gameboardMock.getProperties()).thenReturn(propertyGroups);

    BuyPropertyPlayerAction action = new BuyPropertyPlayerAction(contextMock);

    // when
    Optional<PlayerAction> result = action.act(player);

    // then
    assertEquals(player, residentialSpace.getOwner());
    assertThat(player.getProperties()).contains(residentialSpace);
    assertThat(result.isPresent()).isFalse();
    assertEquals(20, residentialSpace.getRent());
  }

}
