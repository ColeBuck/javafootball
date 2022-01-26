package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class FGAPlay extends Play {

    boolean successful; // was this field goal attempt successful?

    public FGAPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {
        Player kicker = game.getOffense().getDepthChart().get(DepthChartPosition.K).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + kicker.getFirstName() + " " + kicker.getLastName()
                + " is attempting a field goal for the " + game.getOffense().getName());
    }

    public void postPlayDisplay() {
        if (successful) {
            game.setPlayDescription("The field goal is good!");
        } else {
            game.setPlayDescription("The field goal is no good!");
        }
    }

    public void simulatePlay() {
        if (calculateFieldGoalAttempt()) {
            game.getStats().get(game.getOffense()).addQuarterPoints(game.getCurrentQuarter(), (short) 3);
            game.setBallPosition((short) 35);
        } else {
            game.flipPossession();
            game.setBallPosition((short) (100 - game.getBallPosition()));
        }
        game.setCurrentDown((short) 1);
        game.setYardsToGo((short) 10);

        postPlayDisplay();
    }

    public Play createNextPlay() {
        if (successful) {
            return new KickoffPlay(game);
        } else {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                return new PassPlay(game);
            } else {
                return new RunPlay(game);
            }
        }
    }

    private boolean calculateFieldGoalAttempt() {
        Random rand = new Random();
        if (rand.nextInt(100) > 30) { // TODO: need a more complex algorithm here
            successful = true;
            return true;
        } else {
            successful = false;
            return false;
        }
    }
}