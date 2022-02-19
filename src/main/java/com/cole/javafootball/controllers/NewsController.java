package com.cole.javafootball.controllers;

import com.cole.javafootball.League;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leagues/{leagueId}/news")
public class NewsController {
    @GetMapping("")
    public String getNews(@PathVariable(value = "leagueId") String leagueId, Model model) {
        League league = League.getLeague(leagueId);
        model.addAttribute("league", league);
        return "news";
    }
}
