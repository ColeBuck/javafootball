package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;

public abstract class ScrimmagePlay extends Play {

    boolean firstDown = false; // was a first down achieved on this play?
    boolean touchdown = false; // was touchdown scored on this play?

    public ScrimmagePlay(Game game) {
        super(game);
    }

    public Play createNextPlay() {

        if (touchdown) {
            if (game.getCurrentQuarter() > 4) {
                return null; // sudden death overtime
            } else {
                return new XPAPlay(game);
            }
        }

        if (game.getTimeLeftQuarter() == 0) {
            if (game.getCurrentQuarter() < 4 || game.getTeamStats().get(game.getAwayTeam()).getTotalPoints() == game
                    .getTeamStats().get(game.getHomeTeam()).getTotalPoints()) {
                game.incrementCurrentQuarter();
                game.setTimeLeftQuarter((short) 900);
            } else {
                return null; // end game if time runs out in 4th quarter
            }
        }

        if (game.getCurrentDown() == 4)

        {
            if (game.getBallPosition() > 60) {
                return new FGAPlay(game);
            } else {
                return new PuntPlay(game);
            }
        } else {
            Random rand = new Random();
            if (rand.nextInt(10) < 6) { // randomly choose between run and pass (for now)
                return new PassPlay(game);
            } else {
                return new RunPlay(game);
            }
        }
    }
}