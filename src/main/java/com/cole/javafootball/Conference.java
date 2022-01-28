package com.cole.javafootball;

import java.util.ArrayList;

public class Conference {

    private static ArrayList<Conference> allConferences = new ArrayList<Conference>();

    private String name;
    private ArrayList<Team> teams = new ArrayList<Team>();

    public Conference(String name) {
        this.name = name;
        allConferences.add(this);
    }

    public static ArrayList<Conference> getAllConferences() {
        return allConferences;
    }

    public static Conference getConferenceByName(String name) {
        for (Conference conference : allConferences) {
            if (conference.name.equals(name)) {
                return conference;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
}
