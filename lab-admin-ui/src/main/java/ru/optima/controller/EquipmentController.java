package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.optima.beans.PackageEquipments;
import ru.optima.repr.EquipmentRepr;
import ru.optima.service.*;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class EquipmentController {

    private final PathCreator pathCreator;
    private final PackageEquipments packageEquipments;
    private final EquipmentServiceImpl equipmentService;
    private final KitService kitService;

    @GetMapping("/equipments")
    public String adminEquipmentsPage(Model model, Principal principal, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Equipments");
        model.addAttribute("equipments", equipmentService.findAll());
        return pathCreator.createPath(auth, "equipments");
    }

    @GetMapping("/equipment/{id}/edit")
    public String adminEditEquipment(Model model, SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Equipment");
        model.addAttribute("equipment", equipmentService.findById(id).orElseThrow(NotFoundException::new));
        return pathCreator.createPath(auth, "equipment_form");
    }

    @GetMapping("/equipment/create")
    public String adminCreateEquipment(Model model, SecurityContextHolder auth) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Equipments"); // TODO ?
        model.addAttribute("equipment", new EquipmentRepr());
        return pathCreator.createPath(auth, "equipment_form");
    }

    @PostMapping("/equipment")
    public String adminUpsertEquipment(@Valid EquipmentRepr equipment, SecurityContextHolder auth, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Equipments");

        if (bindingResult.hasErrors()) {
            return pathCreator.createPath(auth, "equipment_form");
        }

        equipmentService.save(equipment);
        return pathCreator.createPath(auth) + "/equipments";
    }

    @DeleteMapping("/adchief/min/equipment/{id}/delete")
    public String adminDeleteEquipment(Model model, @PathVariable("id") Long id) {
        equipmentService.delete(id);
        return "redirect:/chief/equipments";
    }

    @GetMapping("/equipments_guest")
    public String equipmentsPage(Model model) {
        model.addAttribute("activePage", "Equipments");
        model.addAttribute("equipments", equipmentService.findAll());
        model.addAttribute("kits", kitService.findAll());
        return "equipments_guest";
    }

    @GetMapping("/equipments_guest/package")
    public String packageEquipmentsPage(Model model) {
        model.addAttribute("activePage", "Equipments");
        model.addAttribute("equipments", equipmentService.findAll());
        return "equipments_guest/package";
    }

    @GetMapping("/equipments_guest/package/add/{equipmentId}")
    public void addEquipmentToBagById(@PathVariable Long equipmentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        packageEquipments.add(equipmentService.findByEId(equipmentId));
        System.out.println(packageEquipments.getEquipments());
        response.sendRedirect(request.getHeader("referer"));
    }
}
