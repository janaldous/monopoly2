package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;

import java.util.*;

public class IncomeTaxSpace extends Space {
  public IncomeTaxSpace(
      PlayerActionFactory factory, String propertyName, int fixedTax, int percentTax) {
    super(
        propertyName,
        Arrays.asList(factory.createChargeIncomeTaxPlayerAction(fixedTax, percentTax)));
  }
}
