package bg.softuni.myownproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String viewHome() {
        return "home";
    }

    @GetMapping("/users/login")
    public String viewLogin() {
        return "login";
    }

    @GetMapping("/about")
    public String viewAbout() {
        return "about";
    }

    @GetMapping("/users/registration")
    public String viewRegister() {
        return "register";
    }

    @GetMapping("/enroll")
    public String viewEnroll() {
        return "enroll";
    }

    @GetMapping("/facilities")
    public String viewFacilities() {
        return "facilities";
    }

    @GetMapping("/album")
    public String viewAlbum() {
        return "album";
    }

    @GetMapping("/news")
    public String viewNews() {
        return "news";
    }

    @GetMapping("/membership")
    public String viewMembership() {
        return "membership";
    }




}
