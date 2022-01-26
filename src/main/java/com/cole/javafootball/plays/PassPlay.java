package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class PassPlay extends ScrimmagePlay {

    boolean completed;

    public PassPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {

    }

    public void simulatePlay() {
        if (calculatePassCompletion()) {
            yardsGained = calculateYardsGained();
            game.getStats().get(game.getOffense()).addPassingYards(yardsGained);
            game.setYardsToGo((short) (yardsToGo - yardsGained));

            // update down
            if (game.getYardsToGo() < 1) {
                game.setCurrentDown((short) 1);
                game.setYardsToGo((short) 10);
            } else {
                game.setCurrentDown((short) (currentDown + 1));
            }

            game.setBallPosition((short) (ballPosition + yardsGained));

            // check for touchdown
            if (game.getBallPosition() > 99) {
                touchdown = true;
                game.setBallPosition((short) 98);
                game.getStats().get(game.getOffense()).addQuarterPoints(game.getCurrentQuarter(), (short) 6);
            }

        } else {
            yardsGained = 0;
            game.setCurrentDown((short) (currentDown + 1));
        }

        game.setTimeLeftQuarter((short) (timeLeftQuarter - calculateClockRunOff()));

        postPlayDisplay();
    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + game.getOffense().getName() + " scored a touchdown!");
        } else if (completed) {
            Player quarterback = game.getOffense().getDepthChart().get(DepthChartPosition.QB).get(0);
            game.setPlayDescription(quarterback.getFullName() + " passed for " + yardsGained + " yards.");
        } else {
            Player quarterback = game.getOffense().getDepthChart().get(DepthChartPosition.QB).get(0);
            game.setPlayDescription(quarterback.getFullName() + " threw an incomplete pass.");
        }
    }

    public boolean calculatePassCompletion() {
        Random rand = new Random();
        if (rand.nextInt(10) > 2) {
            completed = true;
            return true;
        } else {
            completed = false;
            return false;
        }
    }
}
