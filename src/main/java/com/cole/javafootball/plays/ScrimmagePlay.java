package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public abstract class ScrimmagePlay extends Play {

    boolean touchdown; // was touchdown scored on this play?

    public ScrimmagePlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void simulatePlay() {

        yardsGained = calculateYardsGained();
        if (possession == game.getAwayTeam()) {
            game.addAwayYards(yardsGained);
        } else {
            game.addHomeYards(yardsGained);
        }
        game.setYardsToGo((short) (yardsToGo - yardsGained));

        // update down
        if (game.getYardsToGo() < 0) {
            game.setCurrentDown((short) 1);
            game.setYardsToGo((short) 10);
        } else {
            game.setCurrentDown((short) (currentDown + 1));
        }

        ballPosition = (short) (ballPosition + yardsGained);
        game.setTimeLeftQuarter((short) (timeLeftQuarter - calculateClockRunOff())); // update clock

        // check for touchdown
        if (ballPosition > 99) {
            touchdown = true;
            if (possession == game.getAwayTeam()) {
                game.addAwayPoints((short) 6);
            } else {
                game.addHomePoints((short) 6);
            }
        }

        postPlayDisplay();
    }

    public Play createNextPlay() {

        if (touchdown) {
            return new XPAPlay(game, possession, game.getCurrentQuarter(), game.getTimeLeftQuarter(), (short) 98,
                    game.getCurrentDown(), game.getYardsToGo());
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
            if (ballPosition > 60) {
                return new FGAPlay(game, possession, game.getCurrentQuarter(), game.getTimeLeftQuarter(), ballPosition,
                        game.getCurrentDown(), game.getYardsToGo());
            } else {
                return new PuntPlay(game, possession, game.getCurrentQuarter(), game.getTimeLeftQuarter(), ballPosition,
                        game.getCurrentDown(), game.getYardsToGo());
            }
        } else {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) { // randomly choose between run and pass (for now)
                return new PassPlay(game, possession, game.getCurrentQuarter(), game.getTimeLeftQuarter(), ballPosition,
                        game.getCurrentDown(), game.getYardsToGo());
            } else {
                return new RunPlay(game, possession, game.getCurrentQuarter(), game.getTimeLeftQuarter(), ballPosition,
                        game.getCurrentDown(), game.getYardsToGo());
            }

        }

    }

    private short calculateYardsGained() {
        Random rand = new Random();
        if (possession == game.getAwayTeam()) {
            float diff = (possession.getOffenseRating() - game.getHomeTeam().getDefenseRating());
            System.out.println(diff / 50);
            return (short) (rand.nextInt(15) * (diff / 50 + 1)); // TODO: need more complex algorithm here
        } else {
            float diff = (possession.getOffenseRating() - game.getAwayTeam().getDefenseRating());
            System.out.println(diff / 50);
            return (short) (rand.nextInt(15) * (diff / 50 + 1)); // TODO: need more complex algorithm here
        }
    }
}