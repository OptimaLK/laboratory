package ru.optima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_SECRETARY")
public class SecretaryController {

    @RequestMapping("/secretary")
    public String indexSecretaryPage(Model model) {
        model.addAttribute("activePage", "None");
        return "secretary/index";
    }
}

