package com.cole.javafootball;

import java.util.ArrayList;
import java.util.Random;

public class NewsArticle {
    private String headline = "";
    private String body = "";
    private short week;

    public NewsArticle(Game game) {
        week = game.getWeek();
        Team winner = game.getWinner();
        Team loser = game.getLoser();
        TeamStats winnerStats = game.getTeamStats().get(winner);
        TeamStats loserStats = game.getTeamStats().get(loser);

        String score = winnerStats.getTotalPoints() + " - " + loserStats.getTotalPoints();

        ArrayList<String> headlinesPool = new ArrayList<String>();

        if (game.getOvertime()) { // overtime

            headlinesPool.add(winner.getName() + " beat " + loser.getName() + " " + winnerStats.getTotalPoints() + " - "
                    + loserStats.getTotalPoints() + " in overtime");
            headlinesPool.add(winner.getName() + " scrape by " + loser.getName() + " " + winnerStats.getTotalPoints()
                    + " - " + loserStats.getTotalPoints() + " in overtime");

        } else if (loserStats.getTotalPoints() == 0) { // shutout

            headlinesPool.add(winner.getName() + " shut out " + loser.getName() + " " + score);

            headlinesPool.add(loser.getName() + " held scoreless in " + score + " loss to " + winner.getName());

        } else if ((game.getTeamStats().get(game.getWinner()).getTotalPoints()
                - game.getTeamStats().get(game.getLoser()).getTotalPoints()) >= 20) { // blowout

            headlinesPool.add(winner.getName() + " blow out " + loser.getName() + " " + score);

            headlinesPool.add(winner.getName() + " demolish " + loser.getName() + " " + score);

            headlinesPool.add(loser.getName() + " struggle in " + score + " loss to " + winner.getName());

        } else if (winnerStats.getTotalPoints() >= 30 && loserStats.getTotalPoints() > 30) { // shootout
            headlinesPool.add(winner.getName() + " beat " + loser.getName() + " " + score + " in shootout");

        } else if (winner.getRecord().getWins() > 3 && winner.getRecord().getLosses() == 0) { // remain undefeated
            headlinesPool.add(winner.getName() + " remain undefeated after " + score + " win over " + loser.getName());

        } else if ((winnerStats.getTotalPoints() - loserStats.getTotalPoints()) <= 7) { // close game
            headlinesPool.add(winner.getName() + " hold on against " + loser.getName() + " " + score);
            headlinesPool.add(winner.getName() + " squeeze past " + loser.getName() + " " + score);
            headlinesPool.add(winner.getName() + " scrape by " + loser.getName() + " " + score);

        } else if (loser.getRecord().getLosses() > 3 && loser.getRecord().getWins() == 0) { // remain winless
            headlinesPool.add(loser.getName() + " still winless after " + score + " loss to " + winner.getName());

        } else { // regular

            headlinesPool.add(winner.getName() + " beat " + loser.getName() + " " + score);
            headlinesPool.add(winner.getName() + " prevail over " + loser.getName() + " " + score);

        }
        // select random headline from pool
        Random random = new Random();
        headline = headlinesPool.get(random.nextInt(headlinesPool.size()));
    }

    public short getWeek() {
        return week;
    }

    public String getWeekString() {
        switch (week) {
        case 19:
            return "League Championship";
        case 18:
            return "Conference Championships";
        case 17:
            return "Conference Semifinals";
        default:
            return "Week " + week;
        }
    }

    public String getHeadline() {
        return headline;
    }

    public String getBody() {
        return body;
    }
}
