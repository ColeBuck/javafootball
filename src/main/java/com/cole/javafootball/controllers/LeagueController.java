package com.cole.javafootball.controllers;

import java.util.UUID;

import com.cole.javafootball.League;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LeagueController {
    @PostMapping("/leagues")
    @ResponseBody
    public String createLeague(Model model) {
        String leagueId = UUID.randomUUID().toString();
        new League(leagueId);
        return leagueId;
    }
}
