package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class PuntPlay extends Play {

    public PuntPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {
        Player punter = game.getOffense().getDepthChart().get(DepthChartPosition.P).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + punter.getFirstName() + " " + punter.getLastName()
                + " is punting for the " + game.getOffense().getName());
    }

    public void postPlayDisplay() {
        game.setPlayDescription("Fair catch.");
    }

    public void simulatePlay() {
        game.flipPossession();
        game.setBallPosition((short) 25);
        game.setCurrentDown((short) 1);
        game.setYardsToGo((short) 10);
        postPlayDisplay();
    }

    public Play createNextPlay() {
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            return new PassPlay(game);
        } else {
            return new RunPlay(game);
        }

    }
}