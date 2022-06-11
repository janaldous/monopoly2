package com.janaldous.monopoly.rules;

import com.janaldous.monopoly.Game;
import com.janaldous.monopoly.GameImpl;
import com.janaldous.monopoly.core.Player;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log
public class GameImplTest {

  @Test
  void test() {
    Game game = new GameImpl();

    Map<String, Integer> playerScores = new HashMap<>();

    for (int i = 0; i < 1000; i++) {
      Player winner = game.start();
      playerScores.putIfAbsent(winner.getName(), 0);
      playerScores.put(winner.getName(), playerScores.get(winner.getName()) + 1);
    }

    log.info(playerScores.toString());
  }
}
