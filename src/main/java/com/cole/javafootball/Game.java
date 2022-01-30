package com.cole.javafootball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.cole.javafootball.plays.KickoffPlay;
import com.cole.javafootball.plays.Play;

public class Game {

    public static ArrayList<Game> allGames = new ArrayList<Game>();
    private String id;

    private Team homeTeam;
    private Team awayTeam;
    private Team offense;
    private Team defense;

    public enum Phase {
        Pregame, Active, Postgame
    }

    private Phase currentPhase = Phase.Pregame;

    private HashMap<Team, TeamStats> teamStats = new HashMap<Team, TeamStats>();
    private HashMap<Player, PlayerStats> playerStats = new HashMap<Player, PlayerStats>();

    private short currentQuarter = 1;
    private short timeLeftQuarter = 900;
    private short currentDown = 1;
    private short yardsToGo = 10;
    private short ballPosition = 35;

    private String playDescription = "";

    private ArrayList<Play> plays = new ArrayList<Play>();

    public Game(String id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

        // create stats objects
        teamStats.put(homeTeam, new TeamStats());
        teamStats.put(awayTeam, new TeamStats());
        for (Player player : awayTeam.getActiveRoster()) {
            playerStats.put(player, new PlayerStats());
        }
        for (Player player : homeTeam.getActiveRoster()) {
            playerStats.put(player, new PlayerStats());
        }
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

            if (simulateCoinToss().equals(awayTeam)) {
                offense = awayTeam;
                defense = homeTeam;
            } else {
                offense = homeTeam;
                defense = awayTeam;
            }

            plays.add(new KickoffPlay(this));
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
                if (teamStats.get(awayTeam).getTotalPoints() == teamStats.get(homeTeam).getTotalPoints()) {
                    awayTeam.getRecord().addTie();
                    homeTeam.getRecord().addTie();
                } else if (teamStats.get(awayTeam).getTotalPoints() > teamStats.get(homeTeam).getTotalPoints()) {
                    awayTeam.getRecord().addWin();
                    homeTeam.getRecord().addLoss();
                } else if (teamStats.get(awayTeam).getTotalPoints() < teamStats.get(homeTeam).getTotalPoints()) {
                    awayTeam.getRecord().addLoss();
                    homeTeam.getRecord().addWin();
                }
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

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getOffense() {
        return offense;
    }

    public Team getDefense() {
        return defense;
    }

    public void flipPossession() {
        Team temp = getOffense();
        offense = defense;
        defense = temp;
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
        return String.valueOf(yardsToGo);
    }

    public void setYardsToGo(short yards) {
        yardsToGo = yards;
    }

    public short getBallPosition() {
        return ballPosition;
    }

    public String getBallPositionString() {
        if (ballPosition < 50) {
            return getOffense().getAbbreviation() + " " + getBallPosition();
        } else if (ballPosition > 50) {
            return getDefense().getAbbreviation() + " " + (100 - getBallPosition());
        } else {
            return "50";
        }
    }

    public void setBallPosition(short position) {
        ballPosition = position;
    }

    public String getPlayDescription() {
        return playDescription;
    }

    public void setPlayDescription(String description) {
        playDescription = description;
    }

    public HashMap<Team, TeamStats> getTeamStats() {
        return teamStats;
    }

    public HashMap<Player, PlayerStats> getPlayerStats() {
        return playerStats;
    }

    // return all players with at least one pass attempt
    public ArrayList<Player> getPassers(Team team) {
        ArrayList<Player> passers = new ArrayList<Player>();
        for (Player player : playerStats.keySet()) {
            if (player.getTeam().equals(team) && playerStats.get(player).getPassAttempts() > 0) {
                passers.add(player);
            }
        }
        return passers;
    }

    // return all players with at least one run attempt
    public ArrayList<Player> getRushers(Team team) {
        ArrayList<Player> rushers = new ArrayList<Player>();
        for (Player player : playerStats.keySet()) {
            if (player.getTeam().equals(team) && playerStats.get(player).getRunAttempts() > 0) {
                rushers.add(player);
            }
        }
        return rushers;

    }

    public ArrayList<Player> getKickers(Team team) {
        ArrayList<Player> kickers = new ArrayList<Player>();
        for (Player player : playerStats.keySet()) {
            if (player.getTeam().equals(team) && playerStats.get(player).getFieldGoalAttempts() > 0) {
                kickers.add(player);
            }
        }
        return kickers;

    }

}