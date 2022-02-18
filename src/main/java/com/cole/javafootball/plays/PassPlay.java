package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class PassPlay extends ScrimmagePlay {

    boolean completed;
    boolean interception;

    public PassPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {

    }

    public void simulatePlay() {

        if (calculatePassCompletion()) {
            yardsGained = calculateYardsGained();
            game.setYardsToGo((short) (yardsToGo - yardsGained));

            // update down
            if (game.getYardsToGo() < 1) {
                firstDown = true;
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
            }

        } else {
            yardsGained = 0;
            if (calculateInterception()) {
                game.flipPossession();
                game.setBallPosition((short) (100 - game.getBallPosition()));
                game.setCurrentDown((short) 1);
                game.setYardsToGo((short) 10);
            } else {
                game.setCurrentDown((short) (currentDown + 1));
            }
        }

        game.setTimeLeftQuarter((short) (timeLeftQuarter - calculateClockRunOff()));

        updateStats();
        postPlayDisplay();
    }

    public void updateStats() {
        game.getTeamStats().get(offense).addPlay();
        game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.QB).get(0)).addPassAttempt();

        if (currentDown == 3) {
            game.getTeamStats().get(offense).addThirdDownConversionAttempt();
        }

        if (completed) {
            game.getTeamStats().get(offense).addPassingYards(yardsGained);
            game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.QB).get(0)).addPassCompletion();
            game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.QB).get(0))
                    .addPassYards(yardsGained);
        }

        if (touchdown) {
            game.getTeamStats().get(offense).addQuarterPoints(game.getCurrentQuarter(), (short) 6);
            game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.QB).get(0)).addPassTd();
        }

        if (firstDown) {
            game.getTeamStats().get(offense).addFirstDown();
            if (currentDown == 3) {
                game.getTeamStats().get(offense).addThirdDownConversion();
            }
        }

        if (interception) {
            game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.QB).get(0)).addPassInt();
        }
    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + offense.getName() + " scored a touchdown!");
        } else if (completed) {
            Player quarterback = offense.getDepthChart().get(DepthChartPosition.QB).get(0);
            game.setPlayDescription(quarterback.getFullName() + " passed for " + yardsGained + " yards.");
        } else if (interception) {
            Player quarterback = offense.getDepthChart().get(DepthChartPosition.QB).get(0);
            game.setPlayDescription(quarterback.getFullName() + " threw an interception!.");
        } else {
            Player quarterback = offense.getDepthChart().get(DepthChartPosition.QB).get(0);
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

    private boolean calculateInterception() {
        Random rand = new Random();
        if (rand.nextInt(10) > 8) {
            interception = true;
            return true;
        } else {
            interception = false;
            return false;
        }
    }

    public short calculateYardsGained() {
        Random rand = new Random();
        float diff = (offense.getOffenseRating() - defense.getDefenseRating());
        return (short) (rand.nextInt(18) * (diff / 50 + 1)); // TODO: need more complex algorithm here
    }
}
