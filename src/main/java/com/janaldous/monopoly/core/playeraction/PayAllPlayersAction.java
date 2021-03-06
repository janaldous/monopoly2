package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;

import java.util.List;
import java.util.Optional;

public class PayAllPlayersAction implements PlayerAction {
  private GameContext context;
  private int amountPerPlayer;

  public PayAllPlayersAction(GameContext context, int amountPerPlayer) {
    this.context = context;
    this.amountPerPlayer = amountPerPlayer;
  }

  @Override
  public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
    List<Player> players = context.getPlayers();
    int cost = players.size() * amountPerPlayer;

    players.forEach(
        otherPlayer -> {
          try {
            context.getBank().transfer(player, otherPlayer, amountPerPlayer);
          } catch (NotEnoughMoneyException e) {
            throw new RuntimeException(e);
          }
        });
    return Optional.empty();
  }

  @Override
  public String getName() {
    return "Pay all players";
  }

  @Override
  public String toString() {
    return getName();
  }
}
