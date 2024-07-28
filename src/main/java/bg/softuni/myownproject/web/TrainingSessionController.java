package bg.softuni.myownproject.web;

import bg.softuni.myownproject.model.dto.AddTrainingDTO;
import bg.softuni.myownproject.model.entity.TrainingSession;
import bg.softuni.myownproject.service.CoachService;
import bg.softuni.myownproject.service.TeamService;
import bg.softuni.myownproject.service.TrainingSessionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/training-sessions")
public class TrainingSessionController {

    private final TrainingSessionService trainingSessionService;
    private final CoachService coachService;
    private final TeamService teamService;

    public TrainingSessionController(TrainingSessionService trainingSessionService, CoachService coachService, TeamService teamService) {
        this.trainingSessionService = trainingSessionService;
        this.coachService = coachService;
        this.teamService = teamService;
    }

    @ModelAttribute("trainingSession")
    public AddTrainingDTO trainingSession() {
        return new AddTrainingDTO();
    }

    @GetMapping
    public String getAllTrainingSessions(Model model) {
        List<TrainingSession> trainingSessions = trainingSessionService.findAll();
        model.addAttribute("trainingSessions", trainingSessions);
        return "trainings-list";
    }

    @GetMapping("/add")
    public String viewAddTrainingForm(Model model) {
        model.addAttribute("trainingSession", new AddTrainingDTO());
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("coaches", coachService.findAll());
        return "add-training";
    }

    @PostMapping("/add")
    public String addTrainingSession(
            @Valid @ModelAttribute("trainingSession") AddTrainingDTO trainingSessionDTO,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            model.addAttribute("coaches", coachService.findAll());
            return "add-training";
        }

        trainingSessionService.addTrainingSession(trainingSessionDTO);
        return "redirect:/training-sessions";
    }




}
