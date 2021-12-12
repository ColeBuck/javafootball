package com.cole.javafootball;

import java.util.ArrayList;

import com.cole.javafootball.Player.Position;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {

    @GetMapping("/teams")
    public String getTeams(Model model) {
        model.addAttribute("teams", Team.getAllTeams());
        return "teams";
    }

    @GetMapping("teams/{name}")
    public String getTeam(@PathVariable(value = "name") String name, @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String filterBy, Model model) {

        Team team = Team.getTeamByName(name);
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

        model.addAttribute("team", team);
        model.addAttribute("roster", rosterCopy);
        model.addAttribute("positions", positions);
        return "team";
    }

    @GetMapping("teams/{name}/depthchart")
    public String getDepthChart(@PathVariable(value = "name") String name,
            @RequestParam(required = true) String position, Model model) {

        Team team = Team.getTeamByName(name);
        ArrayList<Player> players = new ArrayList<Player>();
        for (Player p : team.getDepthChart().get(DepthChartPosition.valueOf(position))) {
            players.add(p);
        }

        ArrayList<String> positions = new ArrayList<String>(); // used for populating combo box
        for (Position p : Position.values()) {
            positions.add(p.toString());
        }

        model.addAttribute("team", team);
        model.addAttribute("players", players);
        model.addAttribute("positions", positions);
        model.addAttribute("selectedPosition", position);
        return "depthchart";
    }

}
