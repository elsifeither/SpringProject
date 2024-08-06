package bg.softuni.myownproject.web;

import bg.softuni.myownproject.model.dto.AddTeamDTO;
import bg.softuni.myownproject.model.entity.Team;
import bg.softuni.myownproject.service.CoachService;
import bg.softuni.myownproject.service.PlayerService;
import bg.softuni.myownproject.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final CoachService coachService;

    public TeamController(TeamService teamService, PlayerService playerService, CoachService coachService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @GetMapping("/teams")
    public String getAllTeams(Model model) {
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "teams-list";
    }

    @PostMapping("/teams/{teamId}/players/{playerId}")
    public String deletePlayer(@PathVariable Long teamId, @PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return "redirect:/teams";
    }


    @ModelAttribute("teamDTO")
    public AddTeamDTO AddTeamDTO() {
        return new AddTeamDTO();
    }

    @GetMapping("/add-team")
    public String showAddTeamForm(Model model) {
        model.addAttribute("teamDTO", new AddTeamDTO());
        model.addAttribute("coaches", coachService.findAllCoaches());
        return "add-team";
    }

    @PostMapping("/add-team")
    public String addTeam(AddTeamDTO teamDTO) {
        teamService.saveTeam(teamDTO);
        return "redirect:/teams";
    }
}
