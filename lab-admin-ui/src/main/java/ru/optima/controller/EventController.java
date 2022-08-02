package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.optima.beans.Dairy;
import ru.optima.beans.Mask;
import ru.optima.repr.EventRepr;
import ru.optima.service.EventService;
import ru.optima.service.UserService;
import ru.optima.service.WorkService;
import ru.optima.util.PathCreator;

@RequiredArgsConstructor
@Controller
@RequestMapping("/event")
public class EventController {

    private final PathCreator pathCreator;
    private final EventService eventService;
    private final UserService userService;
    private final WorkService workService;

    @GetMapping({"", "/"})
    public String mainEvent(Model model, SecurityContextHolder auth) {
        model.addAttribute("event", eventService.findAll());

        return pathCreator.createPath(auth, "event");
    }
    @PostMapping({"", "/"})
    public String editUser(@ModelAttribute EventRepr eventRepr,
                           @ModelAttribute Mask mask,
                           SecurityContextHolder auth) {
        Mask dateCheck = eventService.dateCheck(mask);
        eventService.save(eventRepr, dateCheck);
        return "redirect:/calendar";
    }

    @GetMapping("/create")
    public String createEvent(Model model, SecurityContextHolder auth) {
        model.addAttribute("event", new EventRepr());
        model.addAttribute("mask", new Mask());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("works", workService.findAll());
        return pathCreator.createPath(auth, "event_form");
    }

}
