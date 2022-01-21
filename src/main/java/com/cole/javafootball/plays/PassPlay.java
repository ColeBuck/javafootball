package com.cole.javafootball.plays;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class PassPlay extends ScrimmagePlay {
    public PassPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void prePlayDisplay() {

    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + possession.getName() + " scored a touchdown!");
        } else {
            game.setPlayDescription("The " + possession.getName() + " passed for " + yardsGained + " yards.");
        }
    }
}
