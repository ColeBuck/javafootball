package com.cole.javafootball.controllers;

import com.cole.javafootball.Conference;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StandingsController {

    @GetMapping("/standings")
    public String getStandings(Model model) {
        model.addAttribute("conferences", Conference.getAllConferences());
        return "standings";
    }

}
