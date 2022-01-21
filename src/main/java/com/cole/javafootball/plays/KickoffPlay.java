package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.Player;
import com.cole.javafootball.Team;

public class KickoffPlay extends Play {

    public KickoffPlay(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        super(game, possession, currentQuarter, timeLeftQuarter, ballPosition, currentDown, yardsToGo);
    }

    public void prePlayDisplay() {
        Player kicker = possession.getDepthChart().get(DepthChartPosition.K).get(0);
        game.setPlayDescription(game.getPlayDescription() + " " + kicker.getFirstName() + " " + kicker.getLastName()
                + " is kicking off for the " + possession.getName());
    }

    public void postPlayDisplay() {
        game.setPlayDescription("Touchback.");
    }

    public void simulatePlay() {
        postPlayDisplay();
    }

    public Play createNextPlay() {
        // flip possession
        if (possession == game.getAwayTeam()) {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                return new PassPlay(game, game.getHomeTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                        (short) 25, game.getCurrentDown(), game.getYardsToGo());
            } else {
                return new RunPlay(game, game.getHomeTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                        (short) 25, game.getCurrentDown(), game.getYardsToGo());
            }

        } else {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                return new PassPlay(game, game.getAwayTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                        (short) 25, game.getCurrentDown(), game.getYardsToGo());
            } else {
                return new RunPlay(game, game.getAwayTeam(), game.getCurrentQuarter(), game.getTimeLeftQuarter(),
                        (short) 25, game.getCurrentDown(), game.getYardsToGo());
            }
        }
    }
}