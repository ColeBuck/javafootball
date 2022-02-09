package com.cole.javafootball.controllers;

import com.cole.javafootball.League;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LeagueController {
    @PostMapping("/leagues")
    @ResponseBody
    public String createLeague(Model model) {
        League league = new League();
        return league.getId();
    }

    @PutMapping("/leagues/{leagueId}/advance-week")
    @ResponseBody
    public String advanceWeek(@PathVariable(value = "leagueId") String leagueId) {
        League league = League.getLeague(leagueId);
        if (league.getCurrentWeek() >= 16) {
            return "There are no more weeks left!";
        } else if (league.getWeeks().get(league.getCurrentWeek() - 1).allGamesSimmed()) {
            league.advanceWeek();
            return "";
        } else {
            return "All of the current week's games must be simmed first!";
        }
    }
}
