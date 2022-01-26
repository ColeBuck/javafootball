package com.cole.javafootball;

public class TeamStats {

    private short[] quarterPoints = new short[4];

    private short rushingYards = 0;
    private short passingYards = 0;

    private short punts = 0;

    public TeamStats() {

    }

    public short getQuarterPoints(int quarter) {
        return quarterPoints[quarter - 1];
    }

    // needed for thymeleaf
    public short getQ1Points() {
        return quarterPoints[0];
    }

    // needed for thymeleaf
    public short getQ2Points() {
        return quarterPoints[1];
    }

    // needed for thymeleaf
    public short getQ3Points() {
        return quarterPoints[2];
    }

    // needed for thymeleaf
    public short getQ4Points() {
        return quarterPoints[3];
    }

    public void addQuarterPoints(int quarter, short points) {
        quarterPoints[quarter - 1] += points;
    }

    public short getTotalPoints() {
        short totalPoints = 0;
        for (short points : quarterPoints) {
            totalPoints += points;
        }
        return totalPoints;
    }

    public short getTotalYards() {
        return (short) (rushingYards + passingYards);
    }

    public short getPassingYards() {
        return passingYards;
    }

    public void addPassingYards(short yards) {
        passingYards += yards;
    }

    public short getRushingYards() {
        return rushingYards;
    }

    public void addRushingYards(short yards) {
        rushingYards += yards;
    }

    public short getPunts() {
        return punts;
    }

    public void addPunt() {
        punts += 1;
    }

}