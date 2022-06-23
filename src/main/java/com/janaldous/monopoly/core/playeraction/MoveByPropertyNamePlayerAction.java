package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.token.Token;
import com.janaldous.monopoly.core.exception.*;
import java.util.*;

public class MoveByPropertyNamePlayerAction implements PlayerAction {
  private final String propertyName;
  private final GameContext context;

  public MoveByPropertyNamePlayerAction(String propertyName, GameContext context) {
    this.propertyName = propertyName;
    this.context = context;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    Token token = context.getPlayerToken(player);
    Gameboard gameboard = context.getGameboard();
    int endPosition = gameboard.getPositionBySpaceName(propertyName);
    gameboard.moveToPosition(token, endPosition);

    Space endSpace = gameboard.getSpaceBySpaceName(propertyName);

    return Optional.ofNullable(endSpace.getRequiredActions());
  }

  @Override
  public String getName() {
    return "Move by property: " + propertyName;
  }

  @Override
  public String toString() {
    return getName();
  }
}
