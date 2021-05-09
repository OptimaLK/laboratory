package ru.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.service.WorkService;

import java.util.Collection;

@Controller
public class MainController {

    @Autowired
    private WorkService workService;

    @RequestMapping("/")
    public String indexPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "None");
        Collection<? extends GrantedAuthority> authorities = auth.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            switch (authority.getAuthority()) {
                case "ROLE_ADMIN":
                    return "redirect:admin";
                case "ROLE_CHIEF":
                    return "redirect:chief";
                case "ROLE_DIRECTOR":
                    return "redirect:director";
                case "ROLE_EXECUTOR":
                    return "redirect:executor";
                case "ROLE_SECRETARY":
                    return "redirect:secretary";
            }
        }
        return "index";
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