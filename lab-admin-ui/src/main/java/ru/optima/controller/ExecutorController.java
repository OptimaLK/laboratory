package ru.optima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_EXECUTOR")
public class ExecutorController {

    @RequestMapping("/executor")
    public String indexExecutorPage(Model model) {
        model.addAttribute("activePage", "None");
        return "executor/index";
    }
}
