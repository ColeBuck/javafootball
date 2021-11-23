package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class FGAPlay extends Play {

    boolean successful; // was this field goal attempt successful?

    public FGAPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition, short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void simulatePlay() {
        if(calculateFieldGoalAttempt()) {
            if(possession == game.getAwayTeam()) {
                game.addAwayPoints((short) 3);
            } else {
                game.addHomePoints((short) 3);
            }
            new KickoffPlay(game, possession, currentQuarter, timeLeftQuarter, (short) 25, (short) 1, (short) 10);
        } else {
            if(possession == game.getAwayTeam()) {
                new ScrimmagePlay(game, game.getHomeTeam(), currentQuarter, timeLeftQuarter, (short) (100-ballPosition), (short) 1, (short) 10);
            } else {
                new ScrimmagePlay(game, game.getAwayTeam(), currentQuarter, timeLeftQuarter, (short) (100-ballPosition), (short) 1, (short) 10);
            }
        }
    }

    private boolean calculateFieldGoalAttempt() {
        Random rand = new Random();
        if(rand.nextInt(100) > 30) { // TODO: need a more complex algorithm here
            successful = true;
            return true;
        } else {
            successful = false;
            return false;
        }
    }
}