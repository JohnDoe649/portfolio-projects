package com.sg.bullsandcows.controller;

import com.sg.bullsandcows.dto.Game;
import com.sg.bullsandcows.dto.Round;
import com.sg.bullsandcows.service.BullsAndCowsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/bullsandcows")
public class BullsAndCowsController {

    private final BullsAndCowsServiceImpl sl;
    public BullsAndCowsController(BullsAndCowsServiceImpl sl){this.sl = sl;}

    //Assignment is looking for 5 actions in postman. Our first is to create a game (JSON) with POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String begin(@RequestBody Game game){
        //we will generate our game through sl and dao, then since we only need the id we can just return that individually
        //used a string just to make the id stand out more/look nicer
        sl.generateAnswer(game);
        return String.format("Success! Here is the id for your game: %s", game.getId());
    }

    //Second we will make a guess again with POST. This time we're sending a round (JSON) with the user's guess
    //Notes from instructor review: Request param requires the value to receive a parameter
    @PostMapping("/guess")
    public ResponseEntity<Round> guess(@RequestBody Round round){

        //getting the variables we request from the JSON
        int gameId = round.getGameId();
        int guess = round.getGuess();
        Game game = sl.getGame(gameId); //also getting the game for some fail checks

        //We need to check three things: Does the game exist? Is it complete? And is our guess a 4-digit number?
        if(gameId != game.getId()) return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(!Objects.equals(game.getProgress(), "In Progress.")) return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        else if(guess < 1000 || guess >= 10000) return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        else return ResponseEntity.ok(sl.startGuessing(gameId, guess));
    }

    //returns all of our current games (just get on our default url)
    @GetMapping
    public List<Game> game(){
        return sl.getAll();
    }

    //returns the current game, using an @PathVariable to get the id of the game
    @GetMapping("/{id}")
    public ResponseEntity<Game> game(@PathVariable int id){

        Game result = sl.getGame(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    //Finally the rounds, again PathVariable to get the current game's list of rounds.
    @GetMapping("/{id}/rounds")
    public List<Round> rounds(@PathVariable int id){
        return sl.getRounds(id);
    }
}
