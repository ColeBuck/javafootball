package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;
import com.cole.javafootball.Team;

public class XPAPlay extends Play {

    boolean successful; // was this extra point attempt successful?

    public XPAPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void prePlayDisplay() {
        Player kicker = possession.getDepthChart().get(DepthChartPosition.K).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + kicker.getFirstName() + " " + kicker.getLastName()
                + " is attempting an extra point for the " + possession.getName());
    }

    public void postPlayDisplay() {
        if (successful) {
            game.setPlayDescription("The extra point is good!");
        } else {
            game.setPlayDescription("The extra point is no good!");
        }
    }

    public void simulatePlay() {
        if (calculateExtraPointAttempt()) {
            if (possession == game.getAwayTeam()) {
                game.addAwayPoints((short) 1);
            } else {
                game.addHomePoints((short) 1);
            }
        }
        game.setCurrentDown((short) 1);
        game.setYardsToGo((short) 10);
        postPlayDisplay();
    }

    public Play createNextPlay() {
        return new KickoffPlay(game, possession, game.getCurrentQuarter(), game.getTimeLeftQuarter(), (short) 25,
                (short) 1, (short) 10);
    }

    private boolean calculateExtraPointAttempt() {
        Random rand = new Random();
        if (rand.nextInt(10) > 0) { // TODO: need a more complex algorithm here
            successful = true;
            return true;
        } else {
            successful = false;
            return false;
        }
    }
}