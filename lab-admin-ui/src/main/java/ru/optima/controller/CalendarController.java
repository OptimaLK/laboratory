package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.optima.beans.Dairy;
import ru.optima.persist.repo.RoleRepository;
import ru.optima.service.BagServiceImpl;
import ru.optima.service.UserService;
import ru.optima.util.PathCreator;

@RequiredArgsConstructor
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final PathCreator pathCreator;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BagServiceImpl bagService;
    private Dairy dairy = new Dairy();

    @GetMapping({"", "/"})
    public String mainCalendar(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAll());
        return pathCreator.createPath(auth, "calendar");
    }

    @GetMapping("/minus")
    public String minusMonth(Model model, SecurityContextHolder auth){
        dairy.minusMonth();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAll());
        return pathCreator.createPath(auth, "calendar");
    }
    @GetMapping("/plus")
    public String plusMonth(Model model, SecurityContextHolder auth){
        dairy.plusMonth();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAll());
        return pathCreator.createPath(auth, "calendar");
    }
    @GetMapping("/today")
    public String todayMonth(Model model, SecurityContextHolder auth){
        dairy.today();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAll());
        return pathCreator.createPath(auth, "calendar");
    }

    @GetMapping("/day/{day}/{month}/{year}")
    public String today(Model model,
                        @PathVariable Integer day,
                        @PathVariable Integer month,
                        @PathVariable Integer year,
                        SecurityContextHolder auth){
        dairy.listHour();
        dairy.setData(year, month, day);
        model.addAttribute("activePage", "Day");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAllEquipmentsInBag(dairy));
        return pathCreator.createPath(auth, "calendar_day");
    }

    @GetMapping("/minusDay")
    public String minusDay(Model model, SecurityContextHolder auth){
        dairy.minusDay();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAllEquipmentsInBag(dairy));
        return pathCreator.createPath(auth, "calendar_day");
    }

    @GetMapping("/plusDay")
    public String plusDay(Model model, SecurityContextHolder auth){
        dairy.plusDay();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAllEquipmentsInBag(dairy));
        return pathCreator.createPath(auth, "calendar_day");
    }

    @GetMapping("/todayDay")
    public String todayDay(Model model, SecurityContextHolder auth){
        dairy.today();
        model.addAttribute("activePage", "Calendar");
        model.addAttribute("dairy", dairy);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("bags", bagService.findAllEquipmentsInBag(dairy));
        return pathCreator.createPath(auth, "calendar_day");
    }

}
