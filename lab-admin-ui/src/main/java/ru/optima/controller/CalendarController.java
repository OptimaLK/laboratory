package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.util.PathCreator;

@RequiredArgsConstructor
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final PathCreator pathCreator;

    @GetMapping({"","/"})
    public String mainCalendar(Model model, SecurityContextHolder auth){
        model.addAttribute("activePage", "Calendar");
        return pathCreator.createPath(auth, "calendar");
    }
}
