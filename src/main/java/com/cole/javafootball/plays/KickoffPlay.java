package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class KickoffPlay extends Play {

    public KickoffPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {
        Player kicker = game.getOffense().getDepthChart().get(DepthChartPosition.K).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + kicker.getFirstName() + " " + kicker.getLastName()
                + " is kicking off for the " + game.getOffense().getName());
    }

    public void postPlayDisplay() {
        game.setPlayDescription("Touchback.");
    }

    public void simulatePlay() {
        game.flipPossession();
        game.setBallPosition((short) 25);
        postPlayDisplay();
    }

    public Play createNextPlay() {
        Random rand = new Random();
        if (rand.nextInt(10) < 6) {
            return new PassPlay(game);
        } else {
            return new RunPlay(game);
        }

    }
}