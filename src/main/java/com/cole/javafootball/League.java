package com.cole.javafootball;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;

public class League {

    private static HashMap<String, League> allLeagues = new HashMap<String, League>();

    private String id;

    private ArrayList<Conference> conferences = new ArrayList<Conference>();
    private ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<Week> weeks = new ArrayList<Week>();

    public League() {
        this.id = UUID.randomUUID().toString();
        conferences.add(new Conference("Western Conference"));
        conferences.add(new Conference("Eastern Conference"));
        loadTeamData();

        for (int i = 0; i < 16; i++) {
            weeks.add(new Week((short) (i + 1)));
        }
        loadScheduleData();

        allLeagues.put(this.id, this);
    }

    public void loadScheduleData() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/data/Schedule.txt");
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line = null;

            br.readLine(); // skip the first line

            while ((line = br.readLine()) != null) {

                String[] lineData = line.split(",");

                Integer week = Integer.valueOf(lineData[0]) - 1;
                Integer awayIndex = Integer.valueOf(lineData[1]) - 1;
                Integer homeIndex = Integer.valueOf(lineData[2]) - 1;

                weeks.get(week).addGame(new Game(teams.get(homeIndex), teams.get(awayIndex)));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static League getLeague(String id) {
        return allLeagues.get(id);
    }

    public String getId() {
        return id;
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

    public ArrayList<Week> getWeeks() {
        return weeks;
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
        for (Week week : weeks) {
            for (Game game : week.getGames()) {
                if (game.getId().equals(id)) {
                    return game;
                }
            }
        }
        return null;
    }
}
