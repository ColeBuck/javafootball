package com.cole.javafootball;

public class TeamRecord {
    private short wins = 0;
    private short losses = 0;
    private short ties = 0;

    public TeamRecord() {

    }

    public short getWins() {
        return wins;
    }

    public short getLosses() {
        return losses;
    }

    public short getTies() {
        return ties;
    }

    public void addWin() {
        ++wins;
    }

    public void addLoss() {
        ++losses;
    }

    public void addTie() {
        ++ties;
    }
}
