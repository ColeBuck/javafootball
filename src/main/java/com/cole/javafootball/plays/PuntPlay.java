package com.cole.javafootball.plays;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;
import com.cole.javafootball.Team;

public class PuntPlay extends Play {

    public PuntPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void prePlayDisplay() {
        Player punter = possession.getDepthChart().get(DepthChartPosition.P).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + punter.getFirstName() + " " + punter.getLastName()
                + " is punting for the " + possession.getName());
    }

    public void postPlayDisplay() {
        game.setPlayDescription("Fair catch.");
    }

    public void simulatePlay() {
        game.setCurrentDown((short) 1);
        game.setYardsToGo((short) 10);
        postPlayDisplay();
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