package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;

public abstract class ScrimmagePlay extends Play {

    boolean touchdown; // was touchdown scored on this play?

    public ScrimmagePlay(Game game) {
        super(game);
    }

    public void simulatePlay() {

        yardsGained = calculateYardsGained();
        if (game.getOffense() == game.getAwayTeam()) {
            game.addAwayYards(yardsGained);
        } else {
            game.addHomeYards(yardsGained);
        }
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
            if (game.getOffense() == game.getAwayTeam()) {
                game.addAwayPoints((short) 6);
            } else {
                game.addHomePoints((short) 6);
            }
        }

        postPlayDisplay();
    }

    public Play createNextPlay() {

        if (touchdown) {
            return new XPAPlay(game);
        }

        if (game.getTimeLeftQuarter() == 0) {
            if (game.getCurrentQuarter() < 4) {
                game.incrementCurrentQuarter();
                game.setTimeLeftQuarter((short) 900);
            } else {
                return null; // end game if time runs out in 4th quarter
            }
        }

        if (game.getCurrentDown() == 4) {
            if (game.getBallPosition() > 60) {
                return new FGAPlay(game);
            } else {
                return new PuntPlay(game);
            }
        } else {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) { // randomly choose between run and pass (for now)
                return new PassPlay(game);
            } else {
                return new RunPlay(game);
            }

        }

    }

    private short calculateYardsGained() {
        Random rand = new Random();
        float diff = (game.getOffense().getOffenseRating() - game.getDefense().getDefenseRating());
        System.out.println(diff / 50);
        return (short) (rand.nextInt(15) * (diff / 50 + 1)); // TODO: need more complex algorithm here
    }
}