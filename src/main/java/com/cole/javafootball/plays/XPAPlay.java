package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class XPAPlay extends Play {

    boolean successful; // was this extra point attempt successful?

    public XPAPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {
        Player kicker = game.getOffense().getDepthChart().get(DepthChartPosition.K).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + kicker.getFirstName() + " " + kicker.getLastName()
                + " is attempting an extra point for the " + game.getOffense().getName());
    }

    public void postPlayDisplay() {
        if (successful) {
            game.setPlayDescription("The extra point is good!");
        } else {
            game.setPlayDescription("The extra point is no good!");
        }
    }

    public void simulatePlay() {
        calculateExtraPointAttempt();
        game.setBallPosition((short) 35);
        game.setCurrentDown((short) 1);
        game.setYardsToGo((short) 10);

        updateStats();
        postPlayDisplay();
    }

    public void updateStats() {
        game.getPlayerStats().get(game.getOffense().getDepthChart().get(DepthChartPosition.K).get(0))
                .addExtraPointAttempt();

        if (successful) {
            game.getTeamStats().get(game.getOffense()).addQuarterPoints(game.getCurrentQuarter(), (short) 1);
            game.getPlayerStats().get(game.getOffense().getDepthChart().get(DepthChartPosition.K).get(0))
                    .addExtraPointMade();
        }
    }

    public Play createNextPlay() {
        return new KickoffPlay(game);
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