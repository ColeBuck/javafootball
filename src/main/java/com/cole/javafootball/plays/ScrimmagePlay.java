package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;

public abstract class ScrimmagePlay extends Play {

    boolean touchdown; // was touchdown scored on this play?

    public ScrimmagePlay(Game game) {
        super(game);
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

    public short calculateYardsGained() {
        Random rand = new Random();
        float diff = (game.getOffense().getOffenseRating() - game.getDefense().getDefenseRating());
        System.out.println(diff / 50);
        return (short) (rand.nextInt(15) * (diff / 50 + 1)); // TODO: need more complex algorithm here
    }
}