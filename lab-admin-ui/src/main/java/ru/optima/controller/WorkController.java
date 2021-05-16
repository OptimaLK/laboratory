package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.optima.persist.repo.UserRepository;
import ru.optima.repr.WorkRepr;
import ru.optima.service.WorkServiceImpl;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

import java.security.Principal;
import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/work")
public class WorkController {

    private final WorkServiceImpl workService;
    private final UserRepository userRepository;
    private final PathCreator pathCreator;

    @GetMapping({"", "/"})
    public String workPage(Model model, SecurityContextHolder auth, Principal principal) {
        model.addAttribute("activePage", "Work");

//      для каждой роли (роль маленькими буквами) пользователя на клиент передается свой набор данных
        switch (pathCreator.getRole(auth)) {
            case "chief": {
                model.addAttribute("work", workService.findAll());
                break;
            }
            case "executor": {
                String userLogin = principal.getName();
                Long userId = userRepository.findUserByLastName(userLogin).orElseThrow(NotFoundException::new).getId();
                model.addAttribute("work", workService.findAllWorksByUserId(userId));
                break;
            }
            default: {
                model.addAttribute("work", workService.findAll());
            }
        }
        return pathCreator.createPath(auth, "works");
    }

    @GetMapping("/{id}/edit")
    public String editWork(Model model,SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Work"); // TODO ?
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("work", workService.findById(id).orElseThrow(NotFoundException::new));
        return pathCreator.createPath(auth, "work_form");
    }

    @GetMapping("/create")
    public String createWork(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("work", new WorkRepr());
        return pathCreator.createPath(auth, "work_form");
    }

    @DeleteMapping("/{id}/delete")
    public String deleteWork(@PathVariable("id") Long id) {
        workService.delete(id);
        return "redirect:/work";
    }

    /**
     * Создание задания на работу. Доступно только заведующему.
     * @return
     */
    @Secured("ROLE_CHIEF")
    @PostMapping({"", "/"})
    public String createWork(@ModelAttribute WorkRepr work) {
        log.info("Creating new work...");
        work.setRegistrationDate(new Date());
        workService.save(work);
        return "redirect:/work";
    }
}
