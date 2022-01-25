package com.cole.javafootball.plays;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;

public class RunPlay extends ScrimmagePlay {
    public RunPlay(Game game) {
        super(game);
    }

    public void prePlayDisplay() {

    }

    public void postPlayDisplay() {
        if (touchdown) {
            game.setPlayDescription("The " + game.getOffense().getName() + " scored a touchdown!");
        } else {
            Player runningBack = game.getOffense().getDepthChart().get(DepthChartPosition.RB).get(0);
            game.setPlayDescription(runningBack.getFullName() + " ran for " + yardsGained + " yards.");
        }
    }
}
