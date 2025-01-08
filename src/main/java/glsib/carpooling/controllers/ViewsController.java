package glsib.carpooling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ViewsController {
    @GetMapping("/landing")
    public String showLandingPage() {
        return "landing";
    }
    @GetMapping("/unauthorized")
    public String showHomePage() {
        return "unauthorized ";
    }

}
