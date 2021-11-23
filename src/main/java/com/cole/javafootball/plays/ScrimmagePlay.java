package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class ScrimmagePlay extends Play {

    boolean touchdown; // was touchdown scored on this play?

    public ScrimmagePlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition, short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void simulatePlay() {

        yardsGained = calculateYardsGained();
        if(possession == game.getAwayTeam()) {
            game.addAwayYards(yardsGained);
        } else {
            game.addHomeYards(yardsGained);
        }
        yardsToGo = (short) (yardsToGo - yardsGained);
        
        // update down
        if(yardsToGo < 0) {
            currentDown = 1;
            yardsToGo = 10;
        } else {
            currentDown++;
        }

        ballPosition = (short) (ballPosition + yardsGained);
        timeLeftQuarter = (short) (timeLeftQuarter - calculateClockRunOff());
        
        // check for touchdown
        if(ballPosition > 99) {
            touchdown = true;
            if(possession == game.getAwayTeam()) {
                game.addAwayPoints((short)6);
            } else {
                game.addHomePoints((short)6);
            }
            new XPAPlay(game, possession, currentQuarter, timeLeftQuarter, (short) 98, currentDown, yardsToGo);
        } else if(timeLeftQuarter > 0) {
            if(currentDown == 4) {
                if(ballPosition > 67) {
                    new FGAPlay(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
                } else {
                    new PuntPlay(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
                }
            } else {
                new ScrimmagePlay(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
            }
        // TESTING MULTIPLE QUARTERS HERE
        } else if(currentQuarter < 4) {
            if(currentDown == 4) {
                if(ballPosition > 60) {
                    new FGAPlay(game, possession, (short)(currentQuarter + 1), (short) 900, ballPosition, currentDown, yardsToGo);
                } else {
                    new PuntPlay(game, possession, (short)(currentQuarter + 1), (short) 900, ballPosition, currentDown, yardsToGo);
                }
            } else {
                new ScrimmagePlay(game, possession, (short)(currentQuarter + 1), (short) 900, ballPosition, currentDown, yardsToGo);
            }
        }
    }

    private short calculateYardsGained() {
        Random rand = new Random();
        if(possession == game.getAwayTeam()) {
            float diff = (possession.getOffenseRating() - game.getHomeTeam().getDefenseRating());
            System.out.println(diff/50);
            return (short) (rand.nextInt(15) * (diff/50 + 1)); // TODO: need more complex algorithm here
        } else {
            float diff = (possession.getOffenseRating() - game.getAwayTeam().getDefenseRating());
            System.out.println(diff/50);
            return (short) (rand.nextInt(15) * (diff/50 + 1)); // TODO: need more complex algorithm here
        }
        
    }
}