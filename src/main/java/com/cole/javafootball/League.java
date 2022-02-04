package com.cole.javafootball;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;

public class League {

    private static HashMap<String, League> allLeagues = new HashMap<String, League>();

    private String id;

    ArrayList<Conference> conferences = new ArrayList<Conference>();
    ArrayList<Team> teams = new ArrayList<Team>();
    ArrayList<Game> games = new ArrayList<Game>();

    public League(String id) {
        this.id = id;
        conferences.add(new Conference("Western Conference"));
        conferences.add(new Conference("Eastern Conference"));
        loadTeamData();
        allLeagues.put(this.id, this);
    }

    public static League getLeague(String id) {
        return allLeagues.get(id);
    }

    public void loadTeamData() {

        try {
            ClassPathResource classPathResource = new ClassPathResource("static/data/Teams.txt");
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line = null;

            br.readLine(); // skip the first line

            while ((line = br.readLine()) != null) {

                String[] lineData = line.split(",");

                Team temp = new Team(lineData[0], lineData[1], lineData[2], getConferenceByName(lineData[3]));
                // add team to list of all teams
                teams.add(temp);

            }

            br.close();

        } catch (Exception e) {
            System.out.println("ERROR: Team data could not be loaded for league with ID: " + id);
        }
    }

    public ArrayList<Conference> getConferences() {
        return conferences;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Conference getConferenceByName(String name) {
        for (Conference conference : conferences) {
            if (conference.getName().equals(name)) {
                return conference;
            }
        }
        return null;
    }

    public Team getTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }

    public Game getGameById(String id) {
        for (Game game : games) {
            if (game.getId().equals(id)) {
                return game;
            }
        }
        return null;
    }

    public void addGame(Game game) {
        games.add(game);
    }
}
