package com.cole.javafootball.plays;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

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
            Player quarterback = game.getOffense().getDepthChart().get(DepthChartPosition.QB).get(0);
            game.setPlayDescription(quarterback.getFullName() + " passed for " + yardsGained + " yards.");
        }
    }
}
