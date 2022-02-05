package com.cole.javafootball;

import java.util.ArrayList;

public class Week {
    private short number;
    private ArrayList<Game> games = new ArrayList<Game>();

    public Week(short number) {
        this.number = number;
    }

    public short getNumber() {
        return number;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }
}
