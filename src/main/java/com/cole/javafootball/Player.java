package com.cole.javafootball;

import java.util.Random;

public class Player {

    enum Position {
        QB, RB, FB, WR, TE, LT, LG, C, RG, RT, DT, LE, RE, MLB, ROLB, LOLB, CB, FS, SS, K, P, LS;
    }

    private String firstName;
    private String lastName;
    Position position;
    private short height; // inches
    private short weight; // pounds

    public Player() {
        position = Position.values()[new Random().nextInt(Position.values().length)];
        firstName = PlayerGeneration.generateFirstName();
        lastName = PlayerGeneration.generateLastName();
        height = PlayerGeneration.generateHeight(position);
        weight = PlayerGeneration.generateWeight(position);
    }

    public Player(Position position) {
        this.position = position;
        firstName = PlayerGeneration.generateFirstName();
        lastName = PlayerGeneration.generateLastName();
        height = PlayerGeneration.generateHeight(position);
        weight = PlayerGeneration.generateWeight(position);
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

}