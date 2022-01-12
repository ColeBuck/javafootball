package com.cole.javafootball.plays;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class KickoffPlay extends Play {

    public KickoffPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void prePlayDisplay() {
        game.setPlayDescription(game.getPlayDescription() + "\nThe " + possession.getName() + " are kicking off");
    }

    public void postPlayDisplay() {

    }

    public void simulatePlay() {

    }

    public Play createNextPlay() {
        // flip possession
        if (possession == game.getAwayTeam()) {
            return new ScrimmagePlay(game, game.getHomeTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                    (short) 25, game.getCurrentDown(), game.getYardsToGo());
        } else {
            return new ScrimmagePlay(game, game.getAwayTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                    (short) 25, game.getCurrentDown(), game.getYardsToGo());
        }
    }
}