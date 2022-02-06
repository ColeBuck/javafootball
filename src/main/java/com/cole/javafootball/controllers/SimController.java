package com.cole.javafootball.controllers;

import com.cole.javafootball.Game;
import com.cole.javafootball.League;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class SimController {
    @PutMapping("/leagues/{leagueId}/weeks/{week}") // simulate all games in a given week
    public String simWeek(@PathVariable(value = "leagueId") String leagueId,
            @PathVariable(value = "week") String week) {

        League league = League.getLeague(leagueId);
        int weekIndex = Integer.valueOf(week) - 1;
        for (Game game : league.getWeeks().get(weekIndex).getGames()) {
            game.startGame();
            game.simulateGame();
        }

        return "";
    }
}
