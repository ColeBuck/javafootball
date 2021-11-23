package com.cole.javafootball.plays;

import java.util.Random; 
import java.util.Scanner; // used for simming one play at time

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

public abstract class Play {

    public static Scanner myScanner = new Scanner(System.in); // used for simming one play at time

    Game game; // game that this play is associated with
    Team possession; // team currently in possession
    short currentQuarter;
    short timeLeftQuarter; // seconds
	short ballPosition; // 1 = HOME 1 yard line, 99 = AWAY 1 yard line
    short currentDown;
	short yardsToGo;
    short yardsGained;

    public abstract void simulatePlay(); 

    public Play(Game game, Team possession, short currentQuarter, short timeLeftQuarter, short ballPosition, short currentDown, short yardsToGo) {
        this.game = game;
        this.possession = possession;
        this.currentQuarter = currentQuarter;
        this.timeLeftQuarter = timeLeftQuarter;
        this.ballPosition = ballPosition;
        this.currentDown = currentDown;
        this.yardsToGo = yardsToGo;

        simulatePlay();
    }

    public short calculateClockRunOff() {
        Random rand = new Random();
        return (short) (rand.nextInt(7) + 25);
    }
}