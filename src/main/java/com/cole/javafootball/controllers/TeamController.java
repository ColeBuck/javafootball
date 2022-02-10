package com.cole.javafootball.controllers;

import java.util.ArrayList;

import com.cole.javafootball.DepthChartPosition;
import com.cole.javafootball.Game;
import com.cole.javafootball.League;
import com.cole.javafootball.Player;
import com.cole.javafootball.RosterFilterSorter;
import com.cole.javafootball.Team;
import com.cole.javafootball.Week;
import com.cole.javafootball.Player.Position;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {

    @GetMapping("leagues/{leagueId}/teams")
    public String getTeams(@PathVariable(value = "leagueId") String leagueId, Model model) {
        League league = League.getLeague(leagueId);
        model.addAttribute("league", league);
        model.addAttribute("conferences", league.getConferences());
        return "teams";
    }

    @GetMapping("leagues/{leagueId}/teams/{name}")
    public String getTeam(@PathVariable(value = "leagueId") String leagueId, @PathVariable(value = "name") String name,
            @RequestParam(required = false) String sortBy, @RequestParam(required = false) String filterBy,
            Model model) {

        League league = League.getLeague(leagueId);
        Team team = league.getTeamByName(name);

        ArrayList<Player> rosterCopy = new ArrayList<Player>(); // used for sorting and filtering (without modifying the
                                                                // actual roster)

        if (filterBy != null) {
            rosterCopy = RosterFilterSorter.filterByPosition(team.getActiveRoster(), Position.valueOf(filterBy));
        } else {
            for (Player player : team.getActiveRoster()) {
                rosterCopy.add(player);
            }
        }

        if (sortBy != null) {
            switch (sortBy) {
            case "ratingAscending":
                rosterCopy = RosterFilterSorter.sortByRating(rosterCopy, true);
                break;
            case "ratingDescending":
                rosterCopy = RosterFilterSorter.sortByRating(rosterCopy, false);
                break;
            case "lastNameAscending":
                rosterCopy = RosterFilterSorter.sortByLastName(rosterCopy, true);
                break;
            case "lastNameDescending":
                rosterCopy = RosterFilterSorter.sortByLastName(rosterCopy, false);
                break;
            }
        }

        ArrayList<String> positions = new ArrayList<String>(); // used for populating combo box
        for (Position p : Position.values()) {
            positions.add(p.toString());
        }

        // fetch all of a team's games
        ArrayList<Game> schedule = new ArrayList<Game>();
        for (Week week : league.getWeeks()) {
            for (Game game : week.getGames()) {
                if (game.getAwayTeam().equals(team) || game.getHomeTeam().equals(team)) {
                    schedule.add(game);
                }
            }
        }

        model.addAttribute("league", league);
        model.addAttribute("team", team);
        model.addAttribute("schedule", schedule);
        model.addAttribute("roster", rosterCopy);
        model.addAttribute("positions", positions);
        return "team";
    }

    @GetMapping("leagues/{leagueId}/teams/{name}/depthchart")
    public String getDepthChart(@PathVariable(value = "leagueId") String leagueId,
            @PathVariable(value = "name") String name, @RequestParam(required = true) String position, Model model) {

        League league = League.getLeague(leagueId);
        Team team = league.getTeamByName(name);

        ArrayList<Player> players = new ArrayList<Player>();
        for (Player p : team.getDepthChart().get(DepthChartPosition.valueOf(position))) {
            players.add(p);
        }

        ArrayList<String> positions = new ArrayList<String>(); // used for populating combo box
        for (Position p : Position.values()) {
            positions.add(p.toString());
        }

        model.addAttribute("leagueId", leagueId);
        model.addAttribute("team", team);
        model.addAttribute("players", players);
        model.addAttribute("positions", positions);
        model.addAttribute("selectedPosition", position);
        return "depthchart";
    }

}
