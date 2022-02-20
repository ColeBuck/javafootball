package com.cole.javafootball.controllers;

import com.cole.javafootball.Game;
import com.cole.javafootball.League;

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

    @GetMapping("/leagues/{leagueId}/games/{id}")
    public String getGame(@PathVariable(value = "leagueId") String leagueId, @PathVariable(value = "id") String id,
            Model model) {

        League league = League.getLeague(leagueId);

        Game game = league.getGameById(id);
        model.addAttribute("league", league);
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
            model.addAttribute("away_receivers", game.getReceivers(game.getAwayTeam()));
            model.addAttribute("home_receivers", game.getReceivers(game.getHomeTeam()));
            return "game_active";
        case Postgame:
            model.addAttribute("away_passers", game.getPassers(game.getAwayTeam()));
            model.addAttribute("home_passers", game.getPassers(game.getHomeTeam()));
            model.addAttribute("away_rushers", game.getRushers(game.getAwayTeam()));
            model.addAttribute("home_rushers", game.getRushers(game.getHomeTeam()));
            model.addAttribute("away_kickers", game.getKickers(game.getAwayTeam()));
            model.addAttribute("home_kickers", game.getKickers(game.getHomeTeam()));
            model.addAttribute("away_receivers", game.getReceivers(game.getAwayTeam()));
            model.addAttribute("home_receivers", game.getReceivers(game.getHomeTeam()));
            return "game_postgame";
        default:
            return null;
        }
    }

    @PutMapping("/leagues/{leagueId}/games/{id}")
    public String simPlay(@PathVariable(value = "leagueId") String leagueId, @PathVariable(value = "id") String id,
            Model model) {
        League league = League.getLeague(leagueId);
        Game game = league.getGameById(id);
        if (game.getCurrentPhase() == Game.Phase.Pregame) {
            game.startGame();
        } else {
            game.simulatePlay();
        }
        model.addAttribute("leagueId", leagueId);
        model.addAttribute("game", game);
        return "game_active";
    }

    @PutMapping("/leagues/{leagueId}/games/{id}/quarter")
    public String simQuarter(@PathVariable(value = "leagueId") String leagueId, @PathVariable(value = "id") String id,
            Model model) {
        League league = League.getLeague(leagueId);
        Game game = league.getGameById(id);
        game.simulateQuarter();
        model.addAttribute("leagueId", leagueId);
        model.addAttribute("game", game);
        return "game_active";
    }

    @PutMapping("/leagues/{leagueId}/games/{id}/game")
    public String simGame(@PathVariable(value = "leagueId") String leagueId, @PathVariable(value = "id") String id,
            Model model) {
        League league = League.getLeague(leagueId);
        Game game = league.getGameById(id);
        game.simulateGame();
        model.addAttribute("leagueId", leagueId);
        model.addAttribute("game", game);
        return "game_active";
    }

    @GetMapping("/leagues/{leagueId}/games/new")
    public String getCreateGame(@PathVariable(value = "leagueId") String leagueId, Model model) {
        League league = League.getLeague(leagueId);
        model.addAttribute("leagueId", leagueId);
        model.addAttribute("teams", league.getTeams());
        return "creategame";
    }

    @PostMapping("/leagues/{leagueId}/games/new")
    @ResponseBody
    public String createGame(@PathVariable(value = "leagueId") String leagueId,
            @RequestParam(required = true) String awayTeam, @RequestParam(required = true) String homeTeam) {
        League league = League.getLeague(leagueId);
        Game game = new Game(league, league.getCurrentWeek(), league.getTeamByName(homeTeam),
                league.getTeamByName(awayTeam));
        league.getWeeks().get(0).addGame(game);
        return game.getId();
    }
}
