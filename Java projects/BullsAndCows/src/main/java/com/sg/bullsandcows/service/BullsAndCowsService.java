package com.sg.bullsandcows.service;

import com.sg.bullsandcows.dto.Game;
import com.sg.bullsandcows.dto.Round;

import java.util.List;

public interface BullsAndCowsService {

    Game generateAnswer(Game game);

    Round startGuessing(int id, int guess);

    List<Game> getAll();

    Game getGame(int id);

    List<Round> getRounds(int id);

}
