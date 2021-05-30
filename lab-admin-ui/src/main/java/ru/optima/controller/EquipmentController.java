package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Category;
import ru.optima.repr.EquipmentRepr;
import ru.optima.service.*;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/equipment")
public class EquipmentController {


    private final PathCreator pathCreator;
    private final EquipmentServiceImpl equipmentService;
    private final CategoryServiceImpl categoryService;
    private final UserService userService;


    @GetMapping({"","/"})
    public String equipmentsPage(Model model, Principal principal, SecurityContextHolder auth) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("activePage", "Equipments");
        model.addAttribute("categories", categoryList);
        model.addAttribute("equipments", equipmentService.findAllByCategoryId(categoryList.get(0).getId()));
        model.addAttribute("user", userService.findByName(principal.getName()));
        return pathCreator.createPath(auth, "equipments");
    }

    @GetMapping("/{id}")
    public String equipmentsPageWithCategory(Model model, @PathVariable ("id") Long id,  Principal principal, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Equipments");
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("equipments", equipmentService.findAllByCategoryId(id));
        model.addAttribute("user", userService.findByName(principal.getName()));
        return pathCreator.createPath(auth, "equipments");
    }

    @GetMapping("/{id}/edit")
    public String editEquipment(Model model, SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Equipment");
        model.addAttribute("equipment", equipmentService.findById(id).orElseThrow(NotFoundException::new));
        return pathCreator.createPath(auth, "equipment_form");
    }

    @GetMapping("/create")
    public String createEquipment(Model model, SecurityContextHolder auth) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Equipments"); // TODO ?
        model.addAttribute("equipment", new EquipmentRepr());
        return pathCreator.createPath(auth, "equipment_form");
    }

    @PostMapping("")
    public String upsertEquipment(@Valid EquipmentRepr equipment, SecurityContextHolder auth, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Equipments");

        if (bindingResult.hasErrors()) {
            return pathCreator.createPath(auth, "equipment_form");
        }

        equipmentService.save(equipment);
        return pathCreator.createPath(auth) + "/equipments";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteEquipment(Model model, @PathVariable("id") Long id, SecurityContextHolder auth) {
        equipmentService.delete(id);
        return pathCreator.createPath(auth) + "/equipments";
    }
}
