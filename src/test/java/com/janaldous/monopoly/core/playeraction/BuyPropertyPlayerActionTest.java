package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.PlayerImpl;
import com.janaldous.monopoly.core.PropertyGroup;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyPropertyPlayerActionTest {

  @Mock GameContext contextMock;

  @Test
  void testIsValidAction_playerHasSufficientMoney() {
    // given
    Player player = new PlayerImpl("test", 200);
    Space space =
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
    when(contextMock.getPlayerSpace(player)).thenReturn(space);

    BuyPropertyPlayerAction action = new BuyPropertyPlayerAction(contextMock);

    // when
    boolean result = action.isValidAction(player);

    // then
    assertTrue(result);
  }

  @Test
  void testAct() {}
}
