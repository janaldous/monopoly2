package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Optional;

@Log
public class PickCommunityChestCardAction implements PlayerAction {
  private final GameContext context;

  public PickCommunityChestCardAction(GameContext context) {
    this.context = context;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Card card = context.getGameboard().takeCommunityChestCard();

    log.info("card picked: " + card);

    return Optional.of(List.of(card.getPlayerAction()));
  }

  @Override
  public String getName() {
    return "Pick community chest card";
  }

  @Override
  public String toString() {
    return getName();
  }
}
