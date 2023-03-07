package com.sg.bullsandcows.dao;

import com.sg.bullsandcows.dto.Game;
import com.sg.bullsandcows.dto.Round;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BullsAndCowsDaoImpl implements BullsAndCowsDao{

    private static final List<Game> games = new ArrayList<>();

    @Override
    public Game addGame(int answer, Game game) {

        //stream to set our ids
        int nextId = games.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        //Now to fill the values for our game
        game.setId(nextId);
        game.setAnswer(answer);
        game.setProgress("In Progress.");

        //add the game to our list for the other commands and lets return the game to confirm it's been created!
        games.add(game);
        return game;
    }

    @Override
    public Round addRound(int gameId, int guess, int e, int p) {

        Game game = findById(gameId);
        Round round = new Round();

        //Setting our round's guess, time and results which we will then add to our game object.
        round.setGameId(gameId);
        round.setGuess(guess);
        round.setTime(LocalTime.now());
        round.setScore("e:"+e+"p:"+p);
        game.pushRoundList(round);

        return round;
    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(games);
    }

    @Override
    public Game findById(int id) {
        return games.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Round> getRounds(int id) {
        Game game = findById(id);
        return game.getRoundList();
    }
}
