package com.cole.javafootball;

import java.util.Random;

public class Player {

    public enum Position {
        QB, RB, FB, WR, TE, LT, LG, C, RG, RT, DT, LE, RE, MLB, ROLB, LOLB, CB, FS, SS, K, P, LS;
    }

    private String firstName;
    private String lastName;
    Position position;
    private short height; // inches
    private short weight; // pounds

    private PlayerRatings ratings;

    public Player() {
        position = Position.values()[new Random().nextInt(Position.values().length)];
        ratings = new PlayerRatings(position);

        firstName = PlayerGeneration.generateFirstName();
        lastName = PlayerGeneration.generateLastName();
        height = PlayerGeneration.generateHeight(position);
        weight = PlayerGeneration.generateWeight(position);
        PlayerGeneration.generateRatings(this);
    }

    public Player(Position position) {
        this.position = position;
        ratings = new PlayerRatings(this.position);

        firstName = PlayerGeneration.generateFirstName();
        lastName = PlayerGeneration.generateLastName();
        height = PlayerGeneration.generateHeight(position);
        weight = PlayerGeneration.generateWeight(position);
        PlayerGeneration.generateRatings(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
        return this.ratings;
    }

}