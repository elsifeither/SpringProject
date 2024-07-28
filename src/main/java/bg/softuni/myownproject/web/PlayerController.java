package bg.softuni.myownproject.web;


import bg.softuni.myownproject.model.dto.AddPlayerDTO;
import bg.softuni.myownproject.model.dto.UserRegistrationDTO;
import bg.softuni.myownproject.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
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
        return "redirect:/membership";
    }

}
