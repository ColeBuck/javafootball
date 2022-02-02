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

        game.getTeamStats().get(game.getOffense()).addPlay();
        game.getPlayerStats().get(game.getOffense().getDepthChart().get(DepthChartPosition.RB).get(0)).addRunAttempt();

        game.getTeamStats().get(game.getOffense()).addRushingYards(yardsGained);
        game.getPlayerStats().get(game.getOffense().getDepthChart().get(DepthChartPosition.RB).get(0))
                .addRunYards(yardsGained);

        if (currentDown == 3) {
            game.getTeamStats().get(game.getOffense()).addThirdDownConversionAttempt();
        }

        if (touchdown) {
            game.getTeamStats().get(game.getOffense()).addQuarterPoints(game.getCurrentQuarter(), (short) 6);
            game.getPlayerStats().get(game.getOffense().getDepthChart().get(DepthChartPosition.RB).get(0)).addRunTd();
        }

        if (firstDown) {
            game.getTeamStats().get(game.getOffense()).addFirstDown();
            if (currentDown == 3) {
                game.getTeamStats().get(game.getOffense()).addThirdDownConversion();
            }
        }
    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + game.getOffense().getName() + " scored a touchdown!");
        } else {
            Player runningBack = game.getOffense().getDepthChart().get(DepthChartPosition.RB).get(0);
            game.setPlayDescription(runningBack.getFullName() + " ran for " + yardsGained + " yards.");
        }
    }

    public short calculateYardsGained() {
        Random rand = new Random();
        float diff = (game.getOffense().getOffenseRating() - game.getDefense().getDefenseRating());
        System.out.println(diff / 50);
        return (short) (rand.nextInt(12) * (diff / 50 + 1)); // TODO: need more complex algorithm here
    }
}
