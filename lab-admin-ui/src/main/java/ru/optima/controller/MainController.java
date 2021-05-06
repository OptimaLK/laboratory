package ru.optima.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collection;

@Controller
public class MainController {


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
}