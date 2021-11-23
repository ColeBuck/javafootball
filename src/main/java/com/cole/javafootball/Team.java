package com.cole.javafootball;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.util.ResourceUtils;

public class Team {

    public static ArrayList<Team> allTeams = new ArrayList<Team>();

    private String city;
    private String name;
    private String abbreviation;
    
    private short offenseRating;
    private short defenseRating;

    /*
    HashMap<Position, ArrayList<Player>> depthChart = new HashMap<Position, ArrayList<Player>>();

    ArrayList<Player> practiceSquad = new ArrayList<Player>();
	ArrayList<Player> injuredReserve = new ArrayList<Player>();
    */
    private ArrayList<Player> activeRoster = new ArrayList<Player>();

    public Team(String city, String name, String abbreviation) {
        this.city = city;
        this.name = name;
        this.abbreviation = abbreviation;
        populateRoster();
    }

    public void populateRoster() {
        int count = 0;

        while(count < 53) {
            if(count < 3) {
                activeRoster.add(new Player(Player.Position.QB));
            } else if(count < 6) {
                activeRoster.add(new Player(Player.Position.RB));
            } else if(count < 11) {
                activeRoster.add(new Player(Player.Position.WR));
            } else if(count < 14) {
                activeRoster.add(new Player(Player.Position.TE));
            } else if(count < 16) {
                activeRoster.add(new Player(Player.Position.LT));
            } else if(count < 18) {
                activeRoster.add(new Player(Player.Position.LG));
            } else if(count < 20) {
                activeRoster.add(new Player(Player.Position.C));
            } else if(count < 22) {
                activeRoster.add(new Player(Player.Position.RG));
            } else if(count < 24) {
                activeRoster.add(new Player(Player.Position.RT));
            }
            count++;
        }
    }

    public static Team getTeamByName(String name) {
        for(Team t : allTeams) {
            if(t.name.equals(name)) {
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

    public static void loadTeamData() {

        try {
            File teamsFile = ResourceUtils.getFile("classpath:static/data/Teams.txt");
            FileReader fr = new FileReader(teamsFile);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            br.readLine(); // skip the first line

            while((line = br.readLine()) != null) {

                String[] lineData = line.split(",");

                Team temp = new Team(lineData[0],lineData[1], lineData[2]);
                // add team to list of all teams
                allTeams.add(temp);
            
          
            }
            
            br.close();

        } catch(Exception e) {
            System.out.println("ERROR: Team data could not be loaded");
        }

    }
}
