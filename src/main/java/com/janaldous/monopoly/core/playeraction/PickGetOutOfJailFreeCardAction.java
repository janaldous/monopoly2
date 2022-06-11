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
    return null;
  }
}
