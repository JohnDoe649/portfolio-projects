package com.sg.bullsandcows.dao;

import com.sg.bullsandcows.dto.Game;
import com.sg.bullsandcows.dto.Round;

import java.util.List;

public interface BullsAndCowsDao {

    Game addGame(int answer, Game game);

    Round addRound(int id, int guess, int e, int p);

    List<Game> getAll();

    Game findById(int id);

    List<Round> getRounds(int id);
}
