package com.cole.javafootball.plays;

import java.util.Random;

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
        game.setTimeLeftQuarter((short) (timeLeftQuarter - calculateClockRunOff())); // update clock

        // check for touchdown
        if (game.getBallPosition() > 99) {
            touchdown = true;
            game.setBallPosition((short) 98);
        }

        updateStats();
        postPlayDisplay();
    }

    public void updateStats() {

        game.getTeamStats().get(offense).addPlay();
        game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.RB).get(0)).addRunAttempt();

        game.getTeamStats().get(offense).addRushingYards(yardsGained);
        game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.RB).get(0)).addRunYards(yardsGained);

        if (currentDown == 3) {
            game.getTeamStats().get(offense).addThirdDownConversionAttempt();
        }

        if (touchdown) {
            game.getTeamStats().get(offense).addQuarterPoints(game.getCurrentQuarter(), (short) 6);
            game.getPlayerStats().get(offense.getDepthChart().get(DepthChartPosition.RB).get(0)).addRunTd();
        }

        if (firstDown) {
            game.getTeamStats().get(offense).addFirstDown();
            if (currentDown == 3) {
                game.getTeamStats().get(offense).addThirdDownConversion();
            }
        }
    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + offense.getName() + " scored a touchdown!");
        } else {
            Player runningBack = offense.getDepthChart().get(DepthChartPosition.RB).get(0);
            game.setPlayDescription(runningBack.getFullName() + " ran for " + yardsGained + " yards.");
        }
    }

    public short calculateYardsGained() {
        Random rand = new Random();
        float diff = (offense.getOffenseRating() - defense.getDefenseRating());
        return (short) (rand.nextInt(12) * (diff / 50 + 1)); // TODO: need more complex algorithm here
    }
}
