package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.service.BagService;
import ru.optima.service.EquipmentService;
import ru.optima.service.UserService;

import java.security.Principal;

import ru.optima.service.WorkServiceImpl;
import ru.optima.util.PathCreator;
@RequiredArgsConstructor
@Controller
public class MainController {

    private final BagService bagService;
    private final PathCreator pathCreator;
    private final EquipmentService equipmentService;
    private final UserService userService;
    private final WorkServiceImpl workService;

    @RequestMapping("/")
    public String indexPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "None");
        return  pathCreator.createPath(auth);
    }

    @Secured("ROLE_CHIEF")
    @RequestMapping("/chief")
    public String indexChiefPage(Model model) {
        model.addAttribute("activePage", "None");
        model.addAttribute("bags", bagService.findAll());
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
    public String indexExecutorPage(Model model, Principal principal) {
        model.addAttribute("activePage", "None");
        return "executor/index";
    }

    @Secured("ROLE_SECRETARY")
    @RequestMapping("/secretary")
    public String indexSecretaryPage(Model model) {
        model.addAttribute("activePage", "None");
        return "secretary/index";
    }
}