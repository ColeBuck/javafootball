package com.cole.javafootball;

import java.util.ArrayList;
import java.util.Random;

import com.cole.javafootball.plays.KickoffPlay;
import com.cole.javafootball.plays.Play;

public class Game {

    public static ArrayList<Game> allGames = new ArrayList<Game>();
    private String id;

    private Team homeTeam;
    private Team awayTeam;

    public enum Phase {
        Pregame, Active, Postgame
    }

    Phase currentPhase = Phase.Pregame;

    private short homeScore;
    private short awayScore;

    private short currentQuarter = 1;
    private short timeLeftQuarter = 900;
    private short currentDown = 1;
    private short yardsToGo = 10;

    private String playDescription = "";

    private short homeYards = 0; // testing yards stat here
    private short awayYards = 0; // testing yards stat here

    private ArrayList<Play> plays = new ArrayList<Play>();

    public Game(String id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public static ArrayList<Game> getAllGames() {
        return allGames;
    }

    public static Game getGameById(String id) {
        for (Game game : allGames) {
            if (game.id.equals(id)) {
                return game;
            }
        }
        return null;
    }

    public void startGame() {
        if (currentPhase == Phase.Pregame) {
            currentPhase = Phase.Active;
            homeScore = 0;
            awayScore = 0;

            Team coinTossWinner = simulateCoinToss();

            plays.add(new KickoffPlay(this, coinTossWinner, currentQuarter, timeLeftQuarter, (short) 35, currentDown,
                    yardsToGo));
        }
    }

    // simulate a single play
    public void simulatePlay() {
        if (currentPhase == Phase.Active) {
            Play currentPlay = plays.get(plays.size() - 1);
            currentPlay.simulatePlay();
            Play nextPlay = currentPlay.createNextPlay();
            if (nextPlay != null) {
                plays.add(nextPlay);
            } else {
                this.currentPhase = Phase.Postgame;
            }
        }
    }

    // simulate rest of the current quarter
    public void simulateQuarter() {
        if (currentPhase == Phase.Active) {
            short quarterBeingSimmed = currentQuarter;
            Play currentPlay = plays.get(plays.size() - 1);
            while (currentPlay.getQuarter() == quarterBeingSimmed && currentPhase == Phase.Active) {
                simulatePlay();
                currentPlay = plays.get(plays.size() - 1);
            }
        }
    }

    // simulate rest of game
    public void simulateGame() {
        while (currentPhase == Phase.Active) {
            simulateQuarter();
        }
    }

    // returns coin toss winner
    private Team simulateCoinToss() {
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
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

    public short getCurrentQuarter() {
        return currentQuarter;
    }

    public void incrementCurrentQuarter() {
        if (currentQuarter < 4) {
            currentQuarter++;
        }
    }

    public short getTimeLeftQuarter() {
        return timeLeftQuarter;
    }

    public String getTimeLeftQuarterString() {
        if (timeLeftQuarter % 60 < 10) {
            return ((timeLeftQuarter / 60) + ":0" + (timeLeftQuarter % 60));
        } else {
            return ((timeLeftQuarter / 60) + ":" + (timeLeftQuarter % 60));
        }
    }

    public void setTimeLeftQuarter(short time) {
        if (time < 0) {
            timeLeftQuarter = 0;
        } else if (time <= 900) {
            timeLeftQuarter = time;
        }
    }

    public short getCurrentDown() {
        return currentDown;
    }

    public String getCurrentDownString() {
        switch (currentDown) {
        case 1:
            return "1st";
        case 2:
            return "2nd";
        case 3:
            return "3rd";
        case 4:
            return "4th";
        default:
            return null;
        }
    }

    public void setCurrentDown(short down) {
        currentDown = down;
    }

    public short getYardsToGo() {
        return yardsToGo;
    }

    public String getYardsToGoString() {
        if (yardsToGo == 0) {
            return "inches";
        } else {
            return String.valueOf(yardsToGo);
        }
    }

    public void setYardsToGo(short yards) {
        yardsToGo = yards;
    }

    public String getPlayDescription() {
        return playDescription;
    }

    public void setPlayDescription(String description) {
        playDescription = description;
    }
}