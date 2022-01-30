package com.cole.javafootball.controllers;

import java.util.UUID;

import com.cole.javafootball.Game;
import com.cole.javafootball.Team;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @GetMapping("/games/{id}")
    public String getGame(@PathVariable(value = "id") String id, Model model) {
        Game game = Game.getGameById(id);
        model.addAttribute("game", game);
        switch (game.getCurrentPhase()) {
        case Pregame:
            return "game_pregame";
        case Active:
            if (game.getOffense().equals(game.getAwayTeam())) {
                model.addAttribute("possession", "away");
            } else {
                model.addAttribute("possession", "home");
            }
            model.addAttribute("away_passers", game.getPassers(game.getAwayTeam()));
            model.addAttribute("home_passers", game.getPassers(game.getHomeTeam()));
            model.addAttribute("away_rushers", game.getRushers(game.getAwayTeam()));
            model.addAttribute("home_rushers", game.getRushers(game.getHomeTeam()));
            model.addAttribute("away_kickers", game.getKickers(game.getAwayTeam()));
            model.addAttribute("home_kickers", game.getKickers(game.getHomeTeam()));
            return "game_active";
        case Postgame:
            model.addAttribute("away_passers", game.getPassers(game.getAwayTeam()));
            model.addAttribute("home_passers", game.getPassers(game.getHomeTeam()));
            model.addAttribute("away_rushers", game.getRushers(game.getAwayTeam()));
            model.addAttribute("home_rushers", game.getRushers(game.getHomeTeam()));
            model.addAttribute("away_kickers", game.getKickers(game.getAwayTeam()));
            model.addAttribute("home_kickers", game.getKickers(game.getHomeTeam()));
            return "game_postgame";
        default:
            return null;
        }
    }

    @PutMapping("/games/{id}")
    public String simPlay(@PathVariable(value = "id") String id, Model model) {
        Game game = Game.getGameById(id);
        if (game.getCurrentPhase() == Game.Phase.Pregame) {
            game.startGame();
        } else {
            game.simulatePlay();
        }
        model.addAttribute("game", game);
        return "game_active";
    }

    @PutMapping("/games/{id}/quarter")
    public String simQuarter(@PathVariable(value = "id") String id, Model model) {
        Game game = Game.getGameById(id);
        game.simulateQuarter();
        model.addAttribute("game", game);
        return "game_active";
    }

    @PutMapping("/games/{id}/game")
    public String simGame(@PathVariable(value = "id") String id, Model model) {
        Game game = Game.getGameById(id);
        game.simulateGame();
        model.addAttribute("game", game);
        return "game_active";
    }

    @GetMapping("/games/new")
    public String getCreateGame(Model model) {

        model.addAttribute("teams", Team.getAllTeams());
        return "creategame";
    }

    @PostMapping("/games/new")
    @ResponseBody
    public String createGame(@RequestParam(required = true) String awayTeam,
            @RequestParam(required = true) String homeTeam) {

        String id = UUID.randomUUID().toString();
        Game.allGames.add(new Game(id, Team.getTeamByName(homeTeam), Team.getTeamByName(awayTeam)));
        return id;
    }
}
