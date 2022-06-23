package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.space.PropertyGroup;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Optional;

/**
 * Advance token to the nearest Utility. If unowned, you may buy it from the Bank.
 *
 * <p>If owned, throw dice and pay owner a total 10 (ten) times the amount thrown.
 */
public class MoveToNearestRailroadPlayerAction implements PlayerAction {
  private GameContext context;
  private PlayerAction buyProperty;

  public MoveToNearestRailroadPlayerAction(GameContext context, PlayerAction buyProperty) {
    this.context = context;
    this.buyProperty = buyProperty;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Gameboard gameboard = context.getGameboard();
    Token token = context.getPlayerToken(player);
    int steps =
        gameboard.getProperties().get(PropertyGroup.RAILROAD).stream()
            .mapToInt(
                r -> gameboard.getPositionBySpaceName(r.getName()) - gameboard.getPosition(token))
            .min()
            .orElseThrow(
                () -> new RuntimeException("Something went wrong in calculating nearest railroad"));
    PropertySpace nearestUtility = (PropertySpace) gameboard.moveBySteps(token, steps);

    if (nearestUtility.getOwner() != null) {
      return Optional.of(List.of(buyProperty));
    } else {
      int rent = nearestUtility.getRent();
      nearestUtility.getOwner().addMoney(rent);
      try {
        player.pay(rent);
      } catch (NotEnoughMoneyException e) {
        throw new PlayerActionException(e);
      }
      return Optional.empty();
    }
  }

  @Override
  public String getName() {
    return "Move to nearest railroad";
  }

  @Override
  public String toString() {
    return getName();
  }
}
