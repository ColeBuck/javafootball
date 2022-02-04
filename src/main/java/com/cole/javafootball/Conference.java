package com.cole.javafootball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Conference {

    private String name;
    private ArrayList<Team> teams = new ArrayList<Team>();

    public Conference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Team> getStandings() {
        Collections.sort(teams, new Comparator<Team>() {
            public int compare(Team t1, Team t2) {
                if (t2.getRecord().getWins() != t1.getRecord().getWins()) {
                    return t2.getRecord().getWins() - t1.getRecord().getWins();
                } else {
                    return t1.getRecord().getLosses() - t2.getRecord().getLosses();
                }
            }
        });
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
}
