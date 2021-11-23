package com.cole.javafootball;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {

    @GetMapping("/")
    public String getLanding() {
        return "landing";
    }

    @GetMapping("/teams")
    public String getTeams(Model model) {
        model.addAttribute("teams", Team.allTeams);
        return "teams";
    }

    @GetMapping("teams/{name}")
    public String getTeam(@PathVariable(value="name") String name, Model model) {
        model.addAttribute("team", Team.getTeamByName(name));
        return "team";
    }
}