package com.janaldous.monopoly.core.player.playerstrategy;

import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.playeraction.BuyPropertyPlayerAction;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import com.janaldous.monopoly.core.playeraction.SellPropertyPlayerAction;
import com.janaldous.monopoly.core.space.PropertySpace;

public class NormalPlayerStrategy implements PlayerStrategy {

  private final Player player;
  private final GameContext gameContext;

  public NormalPlayerStrategy(Player player, GameContext gameContext) {
    this.player = player;
    this.gameContext = gameContext;
  }

  @Override
  public boolean shouldAct(PlayerAction playerAction) {
    if (playerAction instanceof BuyPropertyPlayerAction) {
      if (gameContext.getPlayerSpace(player) instanceof PropertySpace propertySpace)
      return player.getBalance() > propertySpace.getValue();
    } else if (playerAction instanceof SellPropertyPlayerAction) {
      return false;
    }
    return true;
  }
}
