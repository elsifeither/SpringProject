package bg.softuni.myownproject.web;

import bg.softuni.myownproject.model.dto.AddCoachDTO;
import bg.softuni.myownproject.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @ModelAttribute("coachDTO")
    public AddCoachDTO addCoachDTO() {
        return new AddCoachDTO();
    }

    @GetMapping("/coaches")
    public String viewCoaches() {
        return "coaches";
    }


    @PostMapping("/coaches")
    public String doEnroll(
            @Valid AddCoachDTO addCoachDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("coachDTO",addCoachDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.coachDTO",bindingResult);
            return "redirect:/coaches";
        }

        coachService.addCoach(addCoachDTO);
        return "redirect:/coaches";
    }

}
