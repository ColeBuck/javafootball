package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class FGAPlay extends Play {

    boolean successful; // was this field goal attempt successful?

    public FGAPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void prePlayDisplay() {
        game.setPlayDescription(
                game.getPlayDescription() + "\nThe " + possession.getName() + " are attempting a field goal");
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
            if (possession == game.getAwayTeam()) {
                game.addAwayPoints((short) 3);
            } else {
                game.addHomePoints((short) 3);
            }
        }
        game.setCurrentDown((short) 1);
        game.setYardsToGo((short) 10);

        postPlayDisplay();
    }

    public Play createNextPlay() {
        if (successful) {
            return new KickoffPlay(game, possession, game.getCurrentQuarter(), timeLeftQuarter, (short) 25,
                    game.getCurrentDown(), game.getYardsToGo());
        } else {
            if (possession == game.getAwayTeam()) {
                return new ScrimmagePlay(game, game.getHomeTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                        (short) (100 - ballPosition), game.getCurrentDown(), game.getYardsToGo());
            } else {
                return new ScrimmagePlay(game, game.getAwayTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                        (short) (100 - ballPosition), game.getCurrentDown(), game.getYardsToGo());
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