package bg.softuni.myownproject.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {


    @GetMapping("/enroll")
    public String viewEnroll() {
        return "enroll";
    }
}
