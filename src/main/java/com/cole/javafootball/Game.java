package com.cole.javafootball;

import java.util.Random;

import com.cole.javafootball.plays.KickoffPlay;

public class Game {
    private Team homeTeam;
    private Team awayTeam;

    private boolean played = false; // has the game been played/simmed yet?

    private short homeScore;
    private short awayScore;

    private short homeYards = 0; // testing yards stat here
    private short awayYards = 0; // testing yards stat here

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void simGame() {
        if(!played) {
            played = true;
            homeScore = 0;
            awayScore = 0;

            Team coinTossWinner = simulateCoinToss();

            // this Play object will start a recursive chain of plays, ending once the time runs out
            new KickoffPlay(this, coinTossWinner, (short) 1, (short) 900, (short) 35, (short) 1, (short) 10);
        }
    }

    // returns coin toss winner
    public Team simulateCoinToss() {
        Random rand = new Random();
        if(rand.nextInt(2) == 0) {
            return homeTeam;
        } else {
            return awayTeam;
        }
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public short getAwayScore() {
        return awayScore;
    }

    public void addAwayPoints(short points) {
        awayScore += points;
    }

    public short getHomeScore() {
        return homeScore;
    }

    public void addHomePoints(short points) {
        homeScore += points;
    }

    public short getAwayYards() {
        return awayYards;
    }

    public void addAwayYards(short yards) {
        awayYards += yards;
    }

    public short getHomeYards() {
        return homeYards;
    }

    public void addHomeYards(short yards) {
        homeYards += yards;
    }
}