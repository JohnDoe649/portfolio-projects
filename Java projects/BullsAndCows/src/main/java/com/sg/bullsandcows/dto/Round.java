package com.sg.bullsandcows.dto;

import java.time.LocalTime;

public class Round {

    private int id, gameId, guess;
    private LocalTime time;
    private String score;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getGuess() {
        return guess;
    }

    public void setTime(LocalTime time){this.time = time;}

    public LocalTime getTime() {
        return time;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }
}
