package com.cole.javafootball;

import java.util.ArrayList;

import com.cole.javafootball.Game.Phase;

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

    public boolean allGamesSimmed() {
        for (Game game : games) {
            if (game.getCurrentPhase() != Phase.Postgame) {
                return false;
            }
        }
        return true;
    }
}
