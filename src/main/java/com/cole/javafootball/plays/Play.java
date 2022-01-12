package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public abstract class Play {

    Game game; // game that this play is associated with
    Team possession; // team currently in possession
    short currentQuarter;
    short timeLeftQuarter; // seconds
    short ballPosition; // 1 = OWN 1 yard line, 99 = OPPONENT 1 yard line
    short currentDown;
    short yardsToGo;
    short yardsGained;

    public abstract void prePlayDisplay();

    public abstract void postPlayDisplay();

    public abstract void simulatePlay();

    // returns the next play to be run (returns null if game ends)
    public abstract Play createNextPlay();

    public Play(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition,
            short currentDown, short yardsToGo) {
        this.game = game;
        this.possession = possession;
        this.currentQuarter = currentQuarter;
        this.timeLeftQuarter = timeLeftQuarter;
        this.ballPosition = ballPosition;
        this.currentDown = currentDown;
        this.yardsToGo = yardsToGo;

        prePlayDisplay();
    }

    public short calculateClockRunOff() {
        Random rand = new Random();
        return (short) (rand.nextInt(7) + 25);
    }
}