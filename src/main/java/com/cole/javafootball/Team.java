package com.cole.javafootball;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.cole.javafootball.Player.Position;

import org.springframework.util.ResourceUtils;

public class Team {

    public static ArrayList<Team> allTeams = new ArrayList<Team>();

    private String city;
    private String name;
    private String abbreviation;

    private short offenseRating;
    private short defenseRating;

    private DepthChart depthChart = new DepthChart();

    private ArrayList<Player> activeRoster = new ArrayList<Player>();

    public Team(String city, String name, String abbreviation) {
        this.city = city;
        this.name = name;
        this.abbreviation = abbreviation;
        populateRoster();
        fillDepthChart();
    }

    public void populateRoster() {
        for (int i = 0; i < 3; i++) {
            activeRoster.add(new Player(Player.Position.QB));
        }
        for (int i = 0; i < 3; i++) {
            activeRoster.add(new Player(Player.Position.RB));
        }
        for (int i = 0; i < 6; i++) {
            activeRoster.add(new Player(Player.Position.WR));
        }
        for (int i = 0; i < 4; i++) {
            activeRoster.add(new Player(Player.Position.TE));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.LT));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.LG));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.C));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.RG));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.RT));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.LE));
        }
        for (int i = 0; i < 3; i++) {
            activeRoster.add(new Player(Player.Position.DT));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.RE));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.LOLB));
        }
        for (int i = 0; i < 3; i++) {
            activeRoster.add(new Player(Player.Position.MLB));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.ROLB));
        }
        for (int i = 0; i < 6; i++) {
            activeRoster.add(new Player(Player.Position.CB));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.FS));
        }
        for (int i = 0; i < 2; i++) {
            activeRoster.add(new Player(Player.Position.SS));
        }
        for (int i = 0; i < 1; i++) {
            activeRoster.add(new Player(Player.Position.K));
        }
        for (int i = 0; i < 1; i++) {
            activeRoster.add(new Player(Player.Position.P));
        }
        for (int i = 0; i < 1; i++) {
            activeRoster.add(new Player(Player.Position.LS));
        }
    }

    // auto-fill the team's depth chart based on overall ratings
    public void fillDepthChart() {
        for (Position position : Position.values()) {
            ArrayList<Player> temp = RosterFilterSorter.filterByPosition(getActiveRoster(), position);
            temp = RosterFilterSorter.sortByRating(temp, false);
            while (temp.size() > 0) {
                depthChart.addPlayer(DepthChartPosition.valueOf(position.toString()), temp.remove(0));
            }
        }
    }

    public static Team getTeamByName(String name) {
        for (Team t : allTeams) {
            if (t.name.equals(name)) {
                return t;
            }
        }
        return null;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public short getOffenseRating() {
        return offenseRating;
    }

    public short getDefenseRating() {
        return defenseRating;
    }

    public ArrayList<Player> getActiveRoster() {
        return activeRoster;
    }

    public DepthChart getDepthChart() {
        return depthChart;
    }

    public static void loadTeamData() {

        try {
            File teamsFile = ResourceUtils.getFile("classpath:static/data/Teams.txt");
            FileReader fr = new FileReader(teamsFile);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            br.readLine(); // skip the first line

            while ((line = br.readLine()) != null) {

                String[] lineData = line.split(",");

                Team temp = new Team(lineData[0], lineData[1], lineData[2]);
                // add team to list of all teams
                allTeams.add(temp);

            }

            br.close();

        } catch (Exception e) {
            System.out.println("ERROR: Team data could not be loaded");
        }

    }
}
