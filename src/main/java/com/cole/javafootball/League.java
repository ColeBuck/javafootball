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

    private short currentWeek = 1;

    private ArrayList<Conference> conferences = new ArrayList<Conference>();
    private ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<Week> weeks = new ArrayList<Week>();

    public League() {
        this.id = UUID.randomUUID().toString();
        conferences.add(new Conference("Western Conference"));
        conferences.add(new Conference("Eastern Conference"));
        loadTeamData();

        for (int i = 0; i < 19; i++) {
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

                int week = Integer.valueOf(lineData[0]) - 1;
                int awayIndex = Integer.valueOf(lineData[1]) - 1;
                int homeIndex = Integer.valueOf(lineData[2]) - 1;

                weeks.get(week).addGame(new Game((short) (week + 1), teams.get(homeIndex), teams.get(awayIndex)));
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

    public short getCurrentWeek() {
        return currentWeek;
    }

    public void advanceWeek() {
        switch (currentWeek) {
        case 16:
            createConferenceSemis();
            break;
        case 17:
            createConferenceChampionships();
            break;
        case 18:
            createLeagueChampionship();
            break;
        }
        ++currentWeek;
    }

    public void createConferenceSemis() {
        Week conferenceSemis = weeks.get(17 - 1);
        for (Conference conference : getConferences()) {
            for (int i = 0; i < 4; i++) {
                Team team = conference.getStandings().get(i);
                team.setPlayoffSeed((short) (i + 1));
                conference.getPlayoffTeams().add(team);
            }
            conferenceSemis.addGame(
                    new Game((short) 17, conference.getPlayoffTeams().get(0), conference.getPlayoffTeams().get(3)));
            conferenceSemis.addGame(
                    new Game((short) 17, conference.getPlayoffTeams().get(1), conference.getPlayoffTeams().get(2)));
        }
    }

    public void createConferenceChampionships() {
        Week conferenceChampionships = weeks.get(18 - 1);

        // get winners from previous round
        ArrayList<Team> winners = new ArrayList<Team>();
        for (Game game : weeks.get(17 - 1).getGames()) {
            winners.add(game.getWinner());
        }

        // remove losers from playoff teams
        for (Conference conference : conferences) {
            ArrayList<Team> newPlayoffTeams = new ArrayList<Team>();
            for (Team team : conference.getPlayoffTeams()) {
                if (winners.contains(team)) {
                    newPlayoffTeams.add(team);
                }
            }
            conference.getPlayoffTeams().clear();
            conference.getPlayoffTeams().addAll(newPlayoffTeams);
        }

        for (Conference conference : getConferences()) {
            conferenceChampionships.addGame(
                    new Game((short) 18, conference.getPlayoffTeams().get(0), conference.getPlayoffTeams().get(1)));
        }
    }

    public void createLeagueChampionship() {
        Week leagueChampionship = weeks.get(19 - 1);

        // get winners from previous round
        ArrayList<Team> winners = new ArrayList<Team>();
        for (Game game : weeks.get(18 - 1).getGames()) {
            winners.add(game.getWinner());
        }

        // remove losers from playoff teams
        for (Conference conference : conferences) {
            ArrayList<Team> newPlayoffTeams = new ArrayList<Team>();
            for (Team team : conference.getPlayoffTeams()) {
                if (winners.contains(team)) {
                    newPlayoffTeams.add(team);
                }
            }
            conference.getPlayoffTeams().clear();
            conference.getPlayoffTeams().addAll(newPlayoffTeams);
        }

        leagueChampionship
                .addGame(new Game((short) 19, getConferenceByName("Western Conference").getPlayoffTeams().get(0),
                        getConferenceByName("Eastern Conference").getPlayoffTeams().get(0)));

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
