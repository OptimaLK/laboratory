package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.optima.persist.model.Work;
import ru.optima.persist.repo.UserRepository;
import ru.optima.repr.WorkRepr;
import ru.optima.service.WorkService;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/work")
public class WorkController {

    private final WorkService workService;
    private final UserRepository userRepository;
    private final PathCreator pathCreator;

    @GetMapping({"", "/"})
    public String adminWorkPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("work", workService.findAll());
        return pathCreator.createPath(auth, "works");
    }

    @GetMapping("/{id}/edit")
    public String adminEditWork(Model model,SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Work"); // TODO ?
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("work", workService.findById(id).orElseThrow(NotFoundException::new));
        return pathCreator.createPath(auth, "work_form");
    }

    @GetMapping("/create")
    public String adminCreateWork(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("work", new WorkRepr());
        return pathCreator.createPath(auth, "work_form");
    }

    @PostMapping("/create")
    public String createUser(SecurityContextHolder auth,  WorkRepr workRepr, BindingResult bindingResult, Model model) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("users", userRepository.findAll());

//        if (bindingResult.hasErrors()) {
//            return pathCreator.createPath(auth, "work_form");
//        }

        try {
//            workRepr.setRegistrationDate(LocalDate.now());
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
    public String adminDeleteWork(@PathVariable("id") Long id) {
        workService.delete(id);
        return "redirect:/work";
    }

    /**
     * Создание/редактирование задания на работу. Доступно только заведующему.
     * @return Редирект на страницу со списком работ.
     */
    @Secured("ROLE_CHIEF")
    @PostMapping({"", "/"})
    public String createWork(@ModelAttribute WorkRepr work) {
        // Дата регистрации заявки создаётся автоматически - это момент создания самой заявки.
        work.setRegistrationDate(new Date());
        workService.save(work);
        return "redirect:/work";
    }

}
