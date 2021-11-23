package com.cole.javafootball.plays;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public class KickoffPlay extends Play {

    public KickoffPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition, short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void simulatePlay() {
        // flip possession
        if(possession == game.getAwayTeam()) {
            new ScrimmagePlay(game, game.getHomeTeam(), currentQuarter, timeLeftQuarter, (short) 25, (short) 1, (short) 10);
        } else {
            new ScrimmagePlay(game, game.getAwayTeam(), currentQuarter, timeLeftQuarter, (short) 25, (short) 1, (short) 10);
        }
    }
}