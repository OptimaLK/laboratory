package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.optima.persist.repo.UserRepository;
import ru.optima.repr.WorkRepr;
import ru.optima.service.WorkServiceImpl;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

import java.time.LocalDate;
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
    public String workPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Work");

//      для каждой роли (роль маленькими буквами) пользователя на клиент передается свой набор данных
//      userLogin - login при авторизации
        switch (pathCreator.getRole(auth)) {
            case "executor": {
                String userLogin = pathCreator.getUserLogin(auth);
                Long userId = userRepository.findUserByLastName(userLogin).orElseThrow(NotFoundException::new).getId();
                model.addAttribute("work", workService.findAllWorksByUserId(userId));
                break;
            }
            case "chief": {
                model.addAttribute("work", workService.findAll());
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
        model.addAttribute("whois", pathCreator.getUserLogin(auth));
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

    @Secured("ROLE_CHIEF")
    @PostMapping({"", "/"})
    public String createWork(SecurityContextHolder auth,  WorkRepr workRepr, BindingResult bindingResult, Model model) {
        model.addAttribute("activePage", "Work");

        if (bindingResult.hasErrors()) {
            return pathCreator.createPath(auth, "work_form");
        }

        try {
            workRepr.setRegistrationDate(new Date());
            workService.save(workRepr);
        } catch (Exception e) {
            log.info("Не получилось сохранить объект  " + "\n" +
                    "  private LocalDate registrationDate = " + workRepr.getRegistrationDate() + "\n" +
                    "  private String clientName = " + workRepr.getClientName() + "\n" +
                    "  private String objectName = " + workRepr.getObjectName() + "\n" +
                    "  private String numberContrac = " + workRepr.getNumberContract() + "\n" +
                    "  private List<User> users = " + workRepr.getUsers().toString() + "\n" +
                    "  private String customer = "  + workRepr.getCustomer() + "\n" );
            return pathCreator.createPath(auth, "work_form");
        }
        return "redirect:/work";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteWork(@PathVariable("id") Long id) {
        workService.delete(id);
        return "redirect:/work";
    }

}
