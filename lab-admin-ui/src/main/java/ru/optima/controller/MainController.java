package ru.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.service.WorkService;

import java.util.Collection;
import ru.optima.util.PathCreator;

@Controller
public class MainController {
    @Autowired
    private WorkService workService;
    private PathCreator pathCreator;

    @Autowired
    public MainController(PathCreator pathCreator) {
        this.pathCreator = pathCreator;
    }

    @RequestMapping("/")
    public String indexPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "None");
        return  pathCreator.createPath(auth);
    }

    @Secured("ROLE_CHIEF")
    @RequestMapping("/chief")
    public String indexChiefPage(Model model) {
        model.addAttribute("activePage", "None");
        return "chief/index";
    }

    @Secured("ROLE_DIRECTOR")
    @RequestMapping("/director")
    public String indexDirectorPage(Model model) {
        model.addAttribute("activePage", "None");
        return "director/index";
    }

    @Secured("ROLE_EXECUTOR")
    @RequestMapping("/executor")
    public String indexExecutorPage(Model model) {
        model.addAttribute("activePage", "None");
        return "executor/index";
    }

    @Secured("ROLE_SECRETARY")
    @RequestMapping("/secretary")
    public String indexSecretaryPage(Model model) {
        model.addAttribute("activePage", "None");
        return "secretary/index";
    }

    @RequestMapping("/admin")
    public String indexAdminPage(Model model) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("work", workService.findAll());
        return "admin/index";
    }
}