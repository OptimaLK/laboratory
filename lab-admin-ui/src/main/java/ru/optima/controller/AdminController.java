package ru.optima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {

    @RequestMapping("/admin")
    public String indexAdminPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin/index";
    }

}
