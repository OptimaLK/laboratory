package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.beans.Dairy;
import ru.optima.persist.repo.RoleRepository;
import ru.optima.service.UserService;
import ru.optima.util.PathCreator;

@RequiredArgsConstructor
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final PathCreator pathCreator;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private Dairy dairy = new Dairy();

    @GetMapping({"", "/"})
    public String mainCalendar(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "calendar");
    }

    @GetMapping("/minus")
    public String minusMonth(Model model, SecurityContextHolder auth){
        dairy.minusMonth();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "calendar");
    }
    @GetMapping("/plus")
    public String plusMonth(Model model, SecurityContextHolder auth){
        dairy.plusMonth();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "calendar");
    }
    @GetMapping("/today")
    public String todayMonth(Model model, SecurityContextHolder auth){
        dairy.today();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "calendar");
    }

}
