package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Optional;

public class MoveByStepsPlayerAction implements PlayerAction {
  private final GameContext context;
  private int steps;

  public MoveByStepsPlayerAction(int steps, GameContext context) {
    this.steps = steps;
    this.context = context;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Token token = context.getPlayerToken(player);
    Gameboard gameboard = context.getGameboard();
    gameboard.moveBySteps(token, steps);

    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Move player by steps: " + steps;
  }
}
