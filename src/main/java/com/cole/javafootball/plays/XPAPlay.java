package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class XPAPlay extends Play {

    boolean successful; // was this extra point attempt successful?

    public XPAPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition, short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void simulatePlay() {
        if(calculateExtraPointAttempt()) {
            if(possession == game.getAwayTeam()) {
                game.addAwayPoints((short) 1);
            } else {
                game.addHomePoints((short) 1);
            }
        }
        new KickoffPlay(game, possession, currentQuarter, timeLeftQuarter, (short) 25, (short) 1, (short) 10);
    }

    private boolean calculateExtraPointAttempt() {
        Random rand = new Random();
        if(rand.nextInt(10) > 0) { // TODO: need a more complex algorithm here
            successful = true;
            return true;
        } else {
            successful = false;
            return false;
        }
    }
}