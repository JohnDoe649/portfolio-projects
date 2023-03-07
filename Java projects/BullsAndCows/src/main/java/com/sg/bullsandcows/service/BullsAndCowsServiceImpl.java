package com.sg.bullsandcows.service;

import com.sg.bullsandcows.dao.BullsAndCowsDaoImpl;
import com.sg.bullsandcows.dto.Game;
import com.sg.bullsandcows.dto.Round;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class BullsAndCowsServiceImpl implements BullsAndCowsService{

    private final BullsAndCowsDaoImpl dao;
    public BullsAndCowsServiceImpl(BullsAndCowsDaoImpl dao){this.dao = dao;}

    //using random to generate a truly random number
    Random rng = new Random();

    @Override
    public Game generateAnswer(Game game) {
        //we're using 4 variables since we want to make sure no digit is the same, hence we need them for logical if checks
        //answer will be used to create our new game
        int digit1, digit2, digit3, digit4, answer;

        //Setting our first digit, since we're making a proper 4 digit number we DON'T want it to be 0
        digit1 = rng.nextInt((10 - 1) + 1) + 1;
        answer = digit1 * 1000;

        //when we have a number that's a different digit, we don't want to have it reroll again, so we will be making 3 different do-whiles
        //second digit
        do{
            digit2 = rng.nextInt(10);
        }while(digit2 == digit1);
        answer += (digit2 * 100);

        //third digit
        do{
            digit3 = rng.nextInt(10);
        }while(digit3 == digit1 || digit3 == digit2);
        answer += (digit3 * 10);

        //fourth digit
        do{
            digit4 = rng.nextInt(10);
        }while(digit4 == digit1 || digit4 == digit2 || digit4 == digit3);
        answer += (digit4);

        //Now that we generated our number, let's add it as a game. We'll be throwing all digits and the full number
        //to save us the trouble of doing more math.
        return dao.addGame(answer, game);
    }

    @Override
    public Round startGuessing(int gameId, int guess) {

        int e = 0, p = 0, digitcheck;
        //Using our findById method to get the current game to use our logic for.
        Game game = dao.findById(gameId);

        int answer = game.getAnswer();

        //2 for loops, one to check for exact(e) matches and the other for partial(p) matches
             for(int i = 4; i >= 1; i--){
                 digitcheck = getDigit(guess, i); //setting this in advance to save on repetition
                 if(digitcheck == getDigit(answer, i)) e++;
                 else{
                     for(int j = 4; j >= 1; j--){
                         if(j == i) j--;
                         if(j > 0) {
                             if (digitcheck == getDigit(answer, j)){
                                 p++;
                                 break; //All of our digits are unique, so we should just break THIS loop once we find a match.
                             }
                         }
                     }
                 }
             }

             //4 exacts completes the game.
             if(e == 4) game.setProgress("Complete! The answer was: " + game.getAnswer());

        return dao.addRound(gameId, guess, e, p);
    }

    @Override
    public List<Game> getAll() {
        return dao.getAll();
    }

    @Override
    public Game getGame(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Round> getRounds(int id) {
        return dao.getRounds(id);
    }

    //We're going to use a Modular Arithmetic method to get our digits and compare them to tally our score
    public int getDigit(int number, int i){
        return (int) ((number / Math.pow(10, i - 1)) % 10);
    }
}
