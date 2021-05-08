package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.repr.WorkRepr;
import ru.optima.service.WorkService;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class WorkController {

    private final WorkService workService;
    private final PathCreator pathCreator;

    @GetMapping("/works")
    public String adminWorkPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("work", workService.findAll());
        return pathCreator.createPath(auth, "works");
    }

    @GetMapping("work/{id}/edit")
    public String adminEditWork(Model model,SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Work"); // TODO ?
        model.addAttribute("work", workService.findById(id).orElseThrow(NotFoundException::new));
        return pathCreator.createPath(auth, "work_form");
    }

    @GetMapping("work/create")
    public String adminCreateWork(Model model, SecurityContextHolder auth) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Work"); // TODO ?
        model.addAttribute("work", new WorkRepr());
        return pathCreator.createPath(auth, "work_form");
    }

    @DeleteMapping("/work/{id}/delete")
    public String adminDeleteWork(Model model, SecurityContextHolder auth, @PathVariable("id") Long id) {
        workService.delete(id);
        return pathCreator.createPath(auth) + "/works";
    }

}
