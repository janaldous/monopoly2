package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.GameContext;
import com.janaldous.monopoly.core.Player;
import com.janaldous.monopoly.core.card.Card;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.Optional;

public class PickCommunityChestCardAction implements PlayerAction {
  private final GameContext context;

  public PickCommunityChestCardAction(GameContext context) {
    this.context = context;
  }

  @Override
  public Optional<PlayerAction> act(Player player) throws PlayerActionException {
    Card card = context.getGameboard().takeCommunityChestCard();

    return Optional.of(card.getPlayerAction());
  }

  @Override
  public String getName() {
    return "";
  }
}
