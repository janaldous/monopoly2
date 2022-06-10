package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.token.Token;

import java.util.Optional;

/**
 * Advance token to the nearest Utility. If unowned, you may buy it from the Bank.
 *
 * <p>If owned, throw dice and pay owner a total 10 (ten) times the amount thrown.
 */
public class MoveToNearestUtilityPlayerAction implements PlayerAction {
  private GameContext context;
  private PlayerAction buyProperty;

  public MoveToNearestUtilityPlayerAction(GameContext context, PlayerAction buyProperty) {
    this.context = context;
    this.buyProperty = buyProperty;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    Gameboard gameboard = context.getGameboard();
    Token token = context.getPlayerToken(player);
    int steps =
        gameboard.getProperties().get(PropertyGroup.UTILITY).stream()
            .mapToInt(
                r -> gameboard.getPositionBySpaceName(r.getName()) - gameboard.getPosition(token))
            .min()
            .orElseThrow(
                () -> new RuntimeException("Something went wrong in calculating nearest utility"));
    PropertySpace nearestUtility = (PropertySpace) gameboard.moveBySteps(token, steps);

    if (!nearestUtility.hasOwner()) {
      return Optional.of(buyProperty);
    } else {
      Dice dice = context.getDice();
      dice.roll();
      int rent = dice.getValue() * 10;
      try {
        context.getBank().transfer(player, nearestUtility.getOwner(), rent);
      } catch (NotEnoughMoneyException e) {
        throw new PlayerActionException(e);
      }
      return Optional.empty();
    }
  }

  @Override
  public String getName() {
    return null;
  }
}
