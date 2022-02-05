package com.cole.javafootball.controllers;

import com.cole.javafootball.League;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ScheduleController {

    @GetMapping("/leagues/{leagueId}/schedule")
    public String getSchedule(@PathVariable(value = "leagueId") String leagueId, Model model) {
        model.addAttribute("league", League.getLeague(leagueId));
        return "schedule";
    }
}
