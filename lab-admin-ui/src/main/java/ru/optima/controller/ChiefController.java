package ru.optima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_CHIEF")
public class ChiefController {

    @RequestMapping("/chif")
    public String indexChiefPage(Model model) {
        model.addAttribute("activePage", "None");
        return "chief/index";
    }
}
