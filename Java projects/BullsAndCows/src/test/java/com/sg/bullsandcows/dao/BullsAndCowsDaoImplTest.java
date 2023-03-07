package com.sg.bullsandcows.dao;

import com.sg.bullsandcows.dto.Game;
import com.sg.bullsandcows.dto.Round;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BullsAndCowsDaoImplTest{

    BullsAndCowsDao dao;

    @BeforeEach
    void set(){dao = new BullsAndCowsDaoImpl();}

    public Game createGame(){
        Game game = new Game();
        return dao.addGame(1234, game);
    }

    public Round createRound(){
        return dao.addRound(1, 1234, 4, 0);
    }

    @Test
    void addGame() {
        Game game = createGame();
        assertEquals(1, game.getId());
    }

    @Test
    void addRound() {
        createGame();
        Round round = createRound();
        assertEquals("e:4p:0", round.getScore());
    }

    @Test
    void getAll() {
        Game game = createGame();
        List<Game> games = dao.getAll();
        assertEquals(game, games.get(0));
    }

    @Test
    void findById() {
        createGame();
        Game game = dao.findById(1);
        assertEquals(1234, game.getAnswer());
    }

    @Test
    void getRounds() {
        createGame();
        Round round = createRound();
        List<Round> rounds = dao.getRounds(1);
        assertEquals(round.getGuess(), (rounds.get(0)).getGuess());
    }
}