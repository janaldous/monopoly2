package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.Space;

import java.util.Optional;

public class PayRentPlayerAction implements PlayerAction {

  private final GameContext context;

  public PayRentPlayerAction(GameContext context) {
    this.context = context;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    Space space = context.getPlayerSpace(player);
    if (space instanceof PropertySpace property) {
      int rent = property.getRent();
      Player owner = property.getOwner();

      // owner should not pay rent
      if (owner.equals(player)) {
        return Optional.empty();
      }

      if (rent > 0) {
        try {
          context.getBank().transfer(player, owner, rent);
        } catch (NotEnoughMoneyException e) {
          throw new PlayerActionException(e);
        }
      }
    }

    return Optional.empty();
  }

  @Override
  public boolean isValidAction(Player player) {
    Space space = context.getPlayerSpace(player);
    return space instanceof PropertySpace property && property.hasOwner();
  }

  @Override
  public String getName() {
    return "";
  }
}
