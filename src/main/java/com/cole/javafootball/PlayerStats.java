package com.cole.javafootball;

public class PlayerStats {

    private Game game;

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

    public PlayerStats(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public short getPassCompletions() {
        return passCompletions;
    }

    public void addPassCompletion() {
        ++passCompletions;
    }

    public short getPassAttempts() {
        return passAttempts;
    }

    public void addPassAttempt() {
        ++passAttempts;
    }

    public short getPassYards() {
        return passYards;
    }

    public void addPassYards(short yards) {
        passYards += yards;
    }

    public short getPassTds() {
        return passTds;
    }

    public void addPassTd() {
        ++passTds;
    }

    public short getPassInts() {
        return passInts;
    }

    public void addPassInt() {
        ++passInts;
    }

    public short getRunAttempts() {
        return runAttempts;
    }

    public void addRunAttempt() {
        ++runAttempts;
    }

    public short getRunYards() {
        return runYards;
    }

    public void addRunYards(short yards) {
        runYards += yards;
    }

    public short getRunTds() {
        return runTds;
    }

    public void addRunTd() {
        ++runTds;
    }

    public short getReceptions() {
        return receptions;
    }

    public void addReception() {
        ++receptions;
    }

    public short getReceivingYards() {
        return receivingYards;
    }

    public void addReceivingYards(short yards) {
        receivingYards += yards;
    }

    public short getReceivingTds() {
        return receivingTds;
    }

    public void addReceivingTd() {
        ++receivingTds;
    }

    public short getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void addFieldGoalMade() {
        ++fieldGoalsMade;
    }

    public short getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public void addFieldGoalAttempt() {
        ++fieldGoalAttempts;
    }

    public short getExtraPointsMade() {
        return extraPointsMade;
    }

    public void addExtraPointMade() {
        ++extraPointsMade;
    }

    public short getExtraPointAttempts() {
        return extraPointAttempts;
    }

    public void addExtraPointAttempt() {
        ++extraPointAttempts;
    }
}
