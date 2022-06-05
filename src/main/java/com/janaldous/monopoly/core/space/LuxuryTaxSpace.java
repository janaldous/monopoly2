package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

import java.util.Arrays;

public class LuxuryTaxSpace extends Space {
  public LuxuryTaxSpace(PlayerActionFactory factory, String propertyName, int tax) {
    super(propertyName, Arrays.asList(factory.createChargePlayerAction(tax)));
  }
}
