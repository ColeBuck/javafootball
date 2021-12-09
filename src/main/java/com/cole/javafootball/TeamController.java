package com.cole.javafootball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        model.addAttribute("teams", Team.allTeams);
        return "teams";
    }

    @GetMapping("teams/{name}")
    public String getTeam(@PathVariable(value = "name") String name, @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String filterBy, Model model) {

        Team team = Team.getTeamByName(name);
        ArrayList<Player> rosterCopy = new ArrayList<Player>(); // used for sorting and filtering (without modifying the
                                                                // actual roster)

        if (filterBy != null) {
            for (Player player : team.getActiveRoster()) {
                if (player.getPosition().equals(filterBy)) {
                    rosterCopy.add(player);
                }
            }
        } else {
            for (Player player : team.getActiveRoster()) {
                rosterCopy.add(player);
            }
        }

        if (sortBy != null) {
            switch (sortBy) {
            case "ratingAscending":
                Collections.sort(rosterCopy, new Comparator<Player>() {
                    public int compare(Player p1, Player p2) {
                        return p1.getRatings().get(Rating.Overall) - p2.getRatings().get(Rating.Overall);
                    }
                });
                break;
            case "ratingDescending":
                Collections.sort(rosterCopy, new Comparator<Player>() {
                    public int compare(Player p1, Player p2) {
                        return p2.getRatings().get(Rating.Overall) - p1.getRatings().get(Rating.Overall);
                    }
                });
                break;
            case "lastNameAscending":
                Collections.sort(rosterCopy, new Comparator<Player>() {
                    public int compare(Player p1, Player p2) {
                        return p1.getLastName().compareTo(p2.getLastName());
                    }
                });
                break;
            case "lastNameDescending":
                Collections.sort(rosterCopy, new Comparator<Player>() {
                    public int compare(Player p1, Player p2) {
                        return p2.getLastName().compareTo(p1.getLastName());
                    }
                });
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

}
