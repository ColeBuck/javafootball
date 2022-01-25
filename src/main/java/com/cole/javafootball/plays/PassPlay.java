package com.cole.javafootball.plays;

import com.cole.javafootball.Game;

public class PassPlay extends ScrimmagePlay {
    public PassPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {

    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + game.getOffense().getName() + " scored a touchdown!");
        } else {
            game.setPlayDescription("The " + game.getOffense().getName() + " passed for " + yardsGained + " yards.");
        }
    }
}
