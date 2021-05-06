package ru.optima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_DIRECTOR")
public class DirectorController {

    @RequestMapping("/director")
    public String indexDirectorPage(Model model) {
        model.addAttribute("activePage", "None");
        return "director/index";
    }
}
