package com.cole.javafootball;

import java.util.Random;
import java.util.UUID;

public class Player {

    public enum Position {
        QB, RB, FB, WR, TE, LT, LG, C, RG, RT, DT, LE, RE, MLB, ROLB, LOLB, CB, FS, SS, K, P, LS;
    }

    private String id;

    private String firstName;
    private String lastName;
    Position position;
    private short height; // inches
    private short weight; // pounds

    private Team team;

    private PlayerRatings ratings;

    private PlayerSeasonStats seasonStats = new PlayerSeasonStats();

    public Player() {
        id = UUID.randomUUID().toString();
        position = Position.values()[new Random().nextInt(Position.values().length)];
        ratings = new PlayerRatings(position);

        firstName = PlayerGeneration.generateFirstName();
        lastName = PlayerGeneration.generateLastName();
        height = PlayerGeneration.generateHeight(position);
        weight = PlayerGeneration.generateWeight(position);
        PlayerGeneration.generateRatings(this);
    }

    public Player(Position position) {
        id = UUID.randomUUID().toString();
        this.position = position;
        ratings = new PlayerRatings(this.position);

        firstName = PlayerGeneration.generateFirstName();
        lastName = PlayerGeneration.generateLastName();
        height = PlayerGeneration.generateHeight(position);
        weight = PlayerGeneration.generateWeight(position);
        PlayerGeneration.generateRatings(this);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPosition() {
        return position.toString();
    }

    public String getHeight() {
        int feet = height / 12;
        int inches = height % 12;
        return String.valueOf(feet) + "'" + String.valueOf(inches) + '"';
    }

    public String getWeight() {
        return weight + " lbs";
    }

    public PlayerRatings getRatings() {
        return ratings;
    }

    public PlayerSeasonStats getStats() {
        return seasonStats;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}