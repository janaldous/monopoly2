package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Optional;

public class GoToJailPlayerAction implements PlayerAction {
  private GameContext context;

  public GoToJailPlayerAction(GameContext context) {
    this.context = context;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Token token = context.getPlayerToken(player);
    context.getGameboard().moveToJail(token);

    return Optional.empty();
  }

  @Override
  public String getName() {
    return null;
  }
}
