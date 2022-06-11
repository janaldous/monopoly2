package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.*;

import java.util.List;
import java.util.Optional;

public interface PlayerAction {
  Optional<List<PlayerAction>> act(Player player) throws PlayerActionException;

  default boolean isValidAction(Player player) {
    return true;
  }

  String getName();

}
