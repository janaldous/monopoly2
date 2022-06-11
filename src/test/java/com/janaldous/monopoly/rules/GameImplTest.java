package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.Game;
import com.janaldous.monopoly.GameImpl;
import com.janaldous.monopoly.core.player.Player;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Log
public class GameImplTest {

  @Test
  void test() {
    Map<String, Integer> playerScores = new HashMap<>();

    for (int i = 0; i < 10; i++) {
      Game game = new GameImpl();
      Player winner = game.start();
      playerScores.putIfAbsent(winner.getName(), 0);
      playerScores.put(winner.getName(), playerScores.get(winner.getName()) + 1);
    }

    log.info(playerScores.toString());
  }
}
