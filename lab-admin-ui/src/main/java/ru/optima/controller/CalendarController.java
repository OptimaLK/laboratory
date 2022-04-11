package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping({"", "/"})
    public String mainCalendar(Model model, SecurityContextHolder auth) {
        Dairy dairy = new Dairy();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("sevenDay", dairy.addsevenDay());
        model.addAttribute("firstDayOfMonth", dairy.firstDayOfMonth());
        model.addAttribute("lastDayOfMonth", dairy.lastDayOfMonth());
        model.addAttribute("monthAndYear", dairy.monthAndYear());
        model.addAttribute("weekOfMonth", dairy.countWeekOfMonth());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "calendar");
    }
}
