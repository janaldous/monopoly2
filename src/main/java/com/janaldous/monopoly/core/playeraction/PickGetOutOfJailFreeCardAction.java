package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.List;
import java.util.Optional;

public class PickGetOutOfJailFreeCardAction implements PlayerAction {

  public PickGetOutOfJailFreeCardAction() {}

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    player.addGetOutOfJailFree();
    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Pick get out of jail free card";
  }

  @Override
  public String toString() {
    return getName();
  }
}
