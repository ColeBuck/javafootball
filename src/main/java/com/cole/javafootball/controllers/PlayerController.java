package com.cole.javafootball.controllers;

import com.cole.javafootball.League;
import com.cole.javafootball.Player;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leagues/{leagueId}/teams/{teamName}/players")
public class PlayerController {
    @GetMapping("/{playerId}")
    public String getPlayer(@PathVariable(value = "leagueId") String leagueId,
            @PathVariable(value = "teamName") String teamName, @PathVariable(value = "playerId") String playerId,
            Model model) {

        League league = League.getLeague(leagueId);
        for (Player player : league.getTeamByName(teamName).getActiveRoster()) {
            if (player.getId().equals(playerId)) {
                model.addAttribute("player", player);
            }
        }
        model.addAttribute("league", league);
        return "player";
    }
}
