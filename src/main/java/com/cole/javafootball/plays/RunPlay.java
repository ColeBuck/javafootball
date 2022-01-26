package com.cole.javafootball.plays;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class RunPlay extends ScrimmagePlay {
    public RunPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {

    }

    public void simulatePlay() {

        yardsGained = calculateYardsGained();
        game.getStats().get(game.getOffense()).addRushingYards(yardsGained);
        game.setYardsToGo((short) (yardsToGo - yardsGained));

        // update down
        if (game.getYardsToGo() < 1) {
            game.setCurrentDown((short) 1);
            game.setYardsToGo((short) 10);
        } else {
            game.setCurrentDown((short) (currentDown + 1));
        }

        game.setBallPosition((short) (ballPosition + yardsGained));
        game.setTimeLeftQuarter((short) (timeLeftQuarter - calculateClockRunOff())); // update clock

        // check for touchdown
        if (game.getBallPosition() > 99) {
            touchdown = true;
            game.setBallPosition((short) 98);
            game.getStats().get(game.getOffense()).addQuarterPoints(game.getCurrentQuarter(), (short) 6);
        }

        postPlayDisplay();
    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + game.getOffense().getName() + " scored a touchdown!");
        } else {
            Player runningBack = game.getOffense().getDepthChart().get(DepthChartPosition.RB).get(0);
            game.setPlayDescription(runningBack.getFullName() + " ran for " + yardsGained + " yards.");
        }
    }
}
