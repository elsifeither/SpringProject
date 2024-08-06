package bg.softuni.myownproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String viewHome() {
        return "home";
    }

    @GetMapping("/about")
    public String viewAbout() {
        return "about";
    }

    @GetMapping("/enrollmentSuccess")
    public String viewEnrollmentSuccess() {
        return "enrollment-success";
    }

    @GetMapping("/facilities-view")
    public String viewFacilities() {
        return "facilities-view";
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
