package bg.softuni.myownproject.web;

import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping("/facilities")
    @PreAuthorize("hasRole('ADMIN')")
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
