package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.optima.persist.model.User;
import ru.optima.persist.model.Work;
import ru.optima.repr.WorkRepr;
import ru.optima.service.UserServiceImpl;
import ru.optima.service.WorkServiceImpl;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/work")
public class WorkController {

    private final WorkServiceImpl workService;
    private final UserServiceImpl userService;
    private final PathCreator pathCreator;

    @GetMapping({"", "/"})
    public String workPage(Model model, SecurityContextHolder auth, Principal principal) {
        model.addAttribute("activePage", "Works");

//      для каждой роли (роль маленькими буквами) пользователя на клиент передается свой набор данных
        switch (pathCreator.getRole(auth)) {
            case "chief": {
                model.addAttribute("work", workService.findAll());
                break;
            }
            case "executor": {
                String userLogin = principal.getName();
                Long userId = userService.findByName(userLogin).getId();
                model.addAttribute("work", workService.findAllActualWorksByUserId(userId));
                break;
            }
            default: {
                model.addAttribute("work", workService.findAll());
            }
        }
        return pathCreator.createPath(auth, "works");
    }

    @GetMapping("/{id}/edit")
    public String editWork(Model model,SecurityContextHolder auth, Principal principal, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Works"); // TODO ?
        if(pathCreator.getRole(auth).equals("chief")) {
            model.addAttribute("users", userService.findAllUserWhoHasRole("ROLE_EXECUTOR"));
            model.addAttribute("work", workService.findById(id).orElseThrow(NotFoundException::new));
        } else if (pathCreator.getRole(auth).equals("executor")) {
            List<User> allUsers = userService.findAllUserWhoHasRole("ROLE_EXECUTOR");
            WorkRepr workRepr = workService.findById(id).orElseThrow(NotFoundException::new);
            allUsers.removeAll(workRepr.getUsers());
            model.addAttribute("users", allUsers);
        }
        model.addAttribute("work", workService.findById(id).orElseThrow(NotFoundException::new));
        return pathCreator.createPath(auth, "work_form");
    }

    @GetMapping("/create")
    public String createWork(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Works");
        model.addAttribute("users", userService.findAllUserWhoHasRole("ROLE_EXECUTOR"));
        model.addAttribute("work", new WorkRepr());
        return pathCreator.createPath(auth, "work_form");
    }

    @DeleteMapping("/{id}/delete")
    public String deleteWork(@PathVariable("id") Long id) {
        workService.delete(id);
        return "redirect:/work";
    }

    @GetMapping ("/archive")
    private String getArchive( Model model, SecurityContextHolder auth, Principal principal) {
        model.addAttribute("activePage", "Archive");
        String userLogin = principal.getName();
        Long userId = userService.findByName(userLogin).getId();
        model.addAttribute("work", workService.findAllInArchiveWorksByUserId(userId));
        return pathCreator.createPath(auth, "archive");
    }

    @PostMapping ("/{id}/take")
    private String getWok( @PathVariable ("id") Long id ) {
        WorkRepr work = workService.findWorkById(id);
        work.setStatus(Work.Status.IN_WORK);
        workService.save(work);
        return "redirect:/work";
    }

    @PostMapping ("/{id}/done")
    private String getDoneWok( @PathVariable ("id") Long id ) {
        WorkRepr work = workService.findWorkById(id);
        work.setStatus(Work.Status.ON_CHECK);
        workService.save(work);
        return "redirect:/work";
    }

    /**
     * Создание/редактирование задания на работу. Доступно только заведующему.
     * @return Редирект на страницу со списком работ.
     */
//    @Secured("ROLE_CHIEF")
    @PostMapping({"", "/"})

    public String createWork(@ModelAttribute WorkRepr work,  SecurityContextHolder auth) {

        log.info("Creating new work...");
        work.setRegistrationDate(new Date());
        if (pathCreator.getRole(auth).equals("executor")) {
            WorkRepr workRepr = workService.findById(work.getId()).orElseThrow(NotFoundException::new);
            // исполнители назначенные начальником
            List<User> executorsList = workRepr.getUsers();
            // добавлены исполнители выбранные в команду исполнителем
            executorsList.addAll(work.getUsers());
            work.setUsers(executorsList);
        }
        work.setStatus(Work.Status.NEW);
        workService.save(work);
        return "redirect:/work";
    }
}
