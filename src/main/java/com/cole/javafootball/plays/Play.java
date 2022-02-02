package com.cole.javafootball.plays;

import java.util.Random;

import com.cole.javafootball.Game;

public abstract class Play {

    Game game; // game that this play is associated with
    short currentQuarter;
    short timeLeftQuarter; // seconds
    short ballPosition; // 1 = OWN 1 yard line, 99 = OPPONENT 1 yard line
    short currentDown;
    short yardsToGo;
    short yardsGained;

    public abstract void prePlayDisplay();

    public abstract void postPlayDisplay();

    public abstract void simulatePlay();

    public abstract void updateStats();

    // returns the next play to be run (returns null if game ends)
    public abstract Play createNextPlay();

    public Play(Game game) {
        this.game = game;
        currentQuarter = game.getCurrentQuarter();
        timeLeftQuarter = game.getTimeLeftQuarter();
        ballPosition = game.getBallPosition();
        currentDown = game.getCurrentDown();
        yardsToGo = game.getYardsToGo();

        prePlayDisplay();
    }

    public short calculateClockRunOff() {
        Random rand = new Random();
        return (short) (rand.nextInt(7) + 25);
    }

    public short getQuarter() {
        return currentQuarter;
    }
}