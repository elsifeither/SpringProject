package bg.softuni.myownproject.web;


import bg.softuni.myownproject.model.dto.AddPlayerDTO;
import bg.softuni.myownproject.model.dto.UserRegistrationDTO;
import bg.softuni.myownproject.model.entity.Team;
import bg.softuni.myownproject.model.entity.UserEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.service.PlayerService;
import bg.softuni.myownproject.service.TeamService;
import bg.softuni.myownproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }


    @ModelAttribute("teams")
    public List<Team> teams() {
        return teamService.findAll();
    }

    @ModelAttribute("playerDTO")
    public AddPlayerDTO addPlayerDTO() {
        return new AddPlayerDTO();
    }


    @GetMapping("/enroll")
    public String viewEnroll() {
        return "enroll";
    }

    @PostMapping("/enroll")
    public String doEnroll(
           @Valid AddPlayerDTO addPlayerDTO,
           BindingResult bindingResult,
           RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("playerDTO",addPlayerDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.playerDTO",bindingResult);
            return "redirect:/enroll";
        }

        playerService.enroll(addPlayerDTO);
        return "redirect:/enrollmentSuccess";
    }



}
