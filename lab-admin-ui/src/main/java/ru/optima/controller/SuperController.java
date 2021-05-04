package ru.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.optima.persist.model.equipments.Category;
import ru.optima.persist.repo.CategoryRepository;
import ru.optima.persist.repo.EquipmentRepository;


@Controller
public class SuperController {

    private final EquipmentRepository equipmentRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SuperController(EquipmentRepository equipmentRepository, CategoryRepository categoryRepository) {
        this.equipmentRepository = equipmentRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("superuser")
    public String superPage(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("equipments", equipmentRepository.findAll());
        return "superuser";
    }

    @GetMapping("superuser/{id}")
    public String superPageID(Model model, @PathVariable("id") Long id) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("equipments", categoryRepository.findById(id).orElse(new Category()).getEquipmens());
        return "superuser";
    }

    @PostMapping("superuser")
    public String superPageInCategory(Model model, Category category) {
        model.addAttribute("activePage", "Category");
        if (category.getId() == null)  {
            return  "redirect:superuser";}
        return "redirect:/superuser/" + category.getId();
    }
}
