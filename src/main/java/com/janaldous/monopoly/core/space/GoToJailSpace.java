package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

import java.util.Arrays;

public class GoToJailSpace extends Space {

  public GoToJailSpace(PlayerActionFactory playerActionFactory) {
    super("Go To Jail", Arrays.asList(playerActionFactory.createGoToJailAction()));
  }
}
