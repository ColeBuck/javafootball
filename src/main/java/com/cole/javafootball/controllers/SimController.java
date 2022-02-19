package com.cole.javafootball.controllers;

import com.cole.javafootball.Game;
import com.cole.javafootball.League;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SimController {
    @PutMapping("/leagues/{leagueId}/weeks/{week}") // simulate all games in a given week
    @ResponseStatus(value = HttpStatus.OK)
    public void simWeek(@PathVariable(value = "leagueId") String leagueId, @PathVariable(value = "week") String week) {

        League league = League.getLeague(leagueId);

        if (league.getCurrentWeek() == Integer.valueOf(week)) { // sim current week
            int weekIndex = Integer.valueOf(week) - 1;
            for (Game game : league.getWeeks().get(weekIndex).getGames()) {
                game.startGame();
                game.simulateGame();
            }
        } else { // sim to week
            while (league.getCurrentWeek() < Integer.valueOf(week)) {
                for (Game game : league.getWeeks().get(league.getCurrentWeek() - 1).getGames()) {
                    game.startGame();
                    game.simulateGame();
                }
                league.advanceWeek();
            }
        }
    }
}
