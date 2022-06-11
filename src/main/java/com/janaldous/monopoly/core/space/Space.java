package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.playeraction.PlayerAction;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ToString
public class Space {
  private final String name;
  private final List<PlayerAction> requiredActions;

  protected Space(String name, List<PlayerAction> requiredActions) {
    this.name = name;
    this.requiredActions = requiredActions;
  }

  public String getName() {
    return name;
  }

  public Map<String, PlayerAction> getPlayerOptions(Player player) {
    return Collections.emptyMap();
  }

  public List<PlayerAction> getRequiredActions() {
    return requiredActions;
  }
}
