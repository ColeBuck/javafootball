package com.cole.javafootball;

import java.util.ArrayList;

import com.cole.javafootball.Player.Position;

public class Team {

    private Conference conference;

    private String city;
    private String name;
    private String abbreviation;

    private short overallRating;
    private short offenseRating;
    private short defenseRating;
    private short specialTeamsRating;

    private TeamRecord record = new TeamRecord();

    private short playoffSeed = -1;

    private DepthChart depthChart = new DepthChart();

    private ArrayList<Player> activeRoster = new ArrayList<Player>();

    public Team(String city, String name, String abbreviation, Conference conference) {
        this.city = city;
        this.name = name;
        this.abbreviation = abbreviation;
        this.conference = conference;
        this.conference.addTeam(this);

        populateRoster();
        fillDepthChart();
        calculateOffenseRating();
        calculateDefenseRating();
        calculateSpecialTeamsRating();
        calculateOverallRating();
    }

    public void populateRoster() {
        for (int i = 0; i < 3; i++) {
            addPlayer(new Player(Player.Position.QB));
        }
        for (int i = 0; i < 3; i++) {
            addPlayer(new Player(Player.Position.RB));
        }
        for (int i = 0; i < 6; i++) {
            addPlayer(new Player(Player.Position.WR));
        }
        for (int i = 0; i < 4; i++) {
            addPlayer(new Player(Player.Position.TE));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.LT));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.LG));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.C));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.RG));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.RT));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.LE));
        }
        for (int i = 0; i < 3; i++) {
            addPlayer(new Player(Player.Position.DT));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.RE));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.LOLB));
        }
        for (int i = 0; i < 3; i++) {
            addPlayer(new Player(Player.Position.MLB));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.ROLB));
        }
        for (int i = 0; i < 6; i++) {
            addPlayer(new Player(Player.Position.CB));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.FS));
        }
        for (int i = 0; i < 2; i++) {
            addPlayer(new Player(Player.Position.SS));
        }
        for (int i = 0; i < 1; i++) {
            addPlayer(new Player(Player.Position.K));
        }
        for (int i = 0; i < 1; i++) {
            addPlayer(new Player(Player.Position.P));
        }
        for (int i = 0; i < 1; i++) {
            addPlayer(new Player(Player.Position.LS));
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

    public Conference getConference() {
        return conference;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public short getPlayoffSeed() {
        return playoffSeed;
    }

    public void setPlayoffSeed(short seed) {
        playoffSeed = seed;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public short getOverallRating() {
        return overallRating;
    }

    public short getOffenseRating() {
        return offenseRating;
    }

    public short getDefenseRating() {
        return defenseRating;
    }

    public short getSpecialTeamsRating() {
        return specialTeamsRating;
    }

    public TeamRecord getRecord() {
        return record;
    }

    public ArrayList<Player> getActiveRoster() {
        return activeRoster;
    }

    public void addPlayer(Player player) {
        activeRoster.add(player);
        player.setTeam(this);
    }

    public DepthChart getDepthChart() {
        return depthChart;
    }

    public void calculateOffenseRating() {
        short ratingsSum = 0;

        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.QB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.RB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.WR).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.WR).get(1).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.WR).get(2).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.TE).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.LT).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.LG).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.C).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.RG).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.RT).get(0).getRatings().get(Rating.Overall));

        this.offenseRating = (short) (ratingsSum / 11);
    }

    public void calculateDefenseRating() {
        short ratingsSum = 0;

        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.LE).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.DT).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.RE).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.LOLB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.MLB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.ROLB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.CB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.CB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.CB).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.FS).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.SS).get(0).getRatings().get(Rating.Overall));

        this.defenseRating = (short) (ratingsSum / 11);
    }

    public void calculateSpecialTeamsRating() {
        short ratingsSum = 0;

        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.K).get(0).getRatings().get(Rating.Overall));
        ratingsSum = (short) (ratingsSum
                + getDepthChart().get(DepthChartPosition.P).get(0).getRatings().get(Rating.Overall));

        this.specialTeamsRating = (short) (ratingsSum / 2);
    }

    public void calculateOverallRating() {
        short ratingsSum = 0;

        ratingsSum = (short) (ratingsSum + getOffenseRating());
        ratingsSum = (short) (ratingsSum + getDefenseRating());
        ratingsSum = (short) (ratingsSum + getSpecialTeamsRating());

        this.overallRating = (short) (ratingsSum / 3);
    }
}
