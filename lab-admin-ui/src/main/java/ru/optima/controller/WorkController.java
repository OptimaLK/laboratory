package ru.optima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.repr.WorkRepr;
import ru.optima.service.WorkService;
import ru.optima.warning.NotFoundException;

@Controller
@RequestMapping("/admin")
public class WorkController {

    private WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/works")
    public String adminWorkPage(Model model) {
        model.addAttribute("activePage", "Work");
        model.addAttribute("work", workService.findAll());
        return "works";
    }

    @GetMapping("work/{id}/edit")
    public String adminEditWork(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Work"); // TODO ?
        model.addAttribute("work", workService.findById(id).orElseThrow(NotFoundException::new));
        return "work_form";
    }

    @GetMapping("work/create")
    public String adminCreateWork(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Work"); // TODO ?
        model.addAttribute("work", new WorkRepr());
        return "work_form";
    }

    @DeleteMapping("/work/{id}/delete")
    public String adminDeleteWork(Model model, @PathVariable("id") Long id) {
        workService.delete(id);
        return "redirect:/works";
    }

}
