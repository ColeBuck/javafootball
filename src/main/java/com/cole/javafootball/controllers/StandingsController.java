package com.cole.javafootball.controllers;

import com.cole.javafootball.League;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StandingsController {

    @GetMapping("leagues/{leagueId}/standings")
    public String getStandings(@PathVariable(value = "leagueId") String leagueId, Model model) {
        League league = League.getLeague(leagueId);
        model.addAttribute("conferences", league.getConferences());
        return "standings";
    }

}
