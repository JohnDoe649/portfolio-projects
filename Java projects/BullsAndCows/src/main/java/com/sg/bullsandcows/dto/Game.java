package com.sg.bullsandcows.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int id, rounds = 0;
    @JsonIgnore //JsonIgnore will ensure the answer will not be displayed when displaying our game
    private int answer;
    private String progress;
    @JsonIgnore
    private List<Round> roundList = new ArrayList<>();

    //int values:
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRounds() {
        return rounds;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgress() {
        return progress;
    }

    public void pushRoundList(Round round) {
        //same stream method here since we want a rounds list in our game object
        int nextId = roundList.stream()
                .mapToInt(Round::getId)
                .max()
                .orElse(0) + 1;

        round.setId(nextId);
        roundList.add(round);
        rounds++;
    }

}
