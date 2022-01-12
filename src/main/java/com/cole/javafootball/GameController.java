package com.cole.javafootball;

import java.util.UUID;

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

        model.addAttribute("game", Game.getGameById(id));
        return "game";
    }

    @PutMapping("/games/{id}")
    public String simGame(@PathVariable(value = "id") String id, Model model) {
        Game game = Game.getGameById(id);
        if (game.currentPhase == Game.Phase.Pregame) {
            game.startGame();
        } else {
            game.simulatePlay();
        }
        model.addAttribute("game", Game.getGameById(id));
        return "game";
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
