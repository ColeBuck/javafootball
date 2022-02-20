package com.cole.javafootball;

import java.util.ArrayList;

public class PlayerSeasonStats {
    private ArrayList<PlayerStats> gameStats = new ArrayList<PlayerStats>();
    private short passCompletions = 0;
    private short passAttempts = 0;
    private short passYards = 0;
    private short passTds = 0;
    private short passInts = 0;

    private short runAttempts = 0;
    private short runYards = 0;
    private short runTds = 0;

    private short receptions = 0;
    private short receivingYards = 0;
    private short receivingTds = 0;

    private short fieldGoalsMade = 0;
    private short fieldGoalAttempts = 0;
    private short extraPointsMade = 0;
    private short extraPointAttempts = 0;

    public PlayerSeasonStats() {

    }

    public ArrayList<PlayerStats> getGameStats() {
        return gameStats;
    }

    public void addGameStats(PlayerStats playerStats) {
        gameStats.add(playerStats);
        passCompletions += playerStats.getPassCompletions();
        passAttempts += playerStats.getPassAttempts();
        passYards += playerStats.getPassYards();
        passTds += playerStats.getPassTds();
        passInts += playerStats.getPassInts();

        runAttempts += playerStats.getRunAttempts();
        runYards += playerStats.getRunYards();
        runTds += playerStats.getRunTds();

        receptions += playerStats.getReceptions();
        receivingYards += playerStats.getReceivingYards();
        receivingTds += playerStats.getReceivingTds();
    }

    public short getPassCompletions() {
        return passCompletions;
    }

    public short getPassAttempts() {
        return passAttempts;
    }

    public short getPassYards() {
        return passYards;
    }

    public short getPassTds() {
        return passTds;
    }

    public short getPassInts() {
        return passInts;
    }

    public short getRunAttempts() {
        return runAttempts;
    }

    public short getRunYards() {
        return runYards;
    }

    public short getRunTds() {
        return runTds;
    }

    public short getReceptions() {
        return receptions;
    }

    public short getReceivingYards() {
        return receivingYards;
    }

    public short getReceivingTds() {
        return receivingTds;
    }

    public short getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public short getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public short getExtraPointsMade() {
        return extraPointsMade;
    }

    public short getExtraPointAttempts() {
        return extraPointAttempts;
    }

}
