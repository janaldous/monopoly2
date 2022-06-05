package com.janaldous.monopoly.core.space;

import com.janaldous.monopoly.core.token.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JailSpace extends Space {

  private List<Token> prisoners;

  public JailSpace() {
    super("Jail", Collections.emptyList());
    prisoners = new ArrayList<>();
  }

  public void jail(Token token) {
    prisoners.add(token);
  }

  public void setFree(Token token) {
    prisoners.remove(token);
  }
}
