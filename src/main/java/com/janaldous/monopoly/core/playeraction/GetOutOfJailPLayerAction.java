package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.List;
import java.util.Optional;

public class GetOutOfJailPLayerAction implements PlayerAction {
  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    player.useGetOutOfJailFree();
    return Optional.empty();
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String toString() {
    return getName();
  }
}
