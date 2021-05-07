package ru.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.optima.beans.PackageEquipments;
import ru.optima.repr.KitRepr;
import ru.optima.repr.EquipmentRepr;
import ru.optima.service.EquipmentServiceImpl;
import ru.optima.service.KitService;
import ru.optima.service.KitServiceImpl;
import ru.optima.service.UserServiceImpl;
import ru.optima.warning.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class EquipmentController {

    private PackageEquipments packageEquipments;
    private EquipmentServiceImpl equipmentService;
    private KitService kitService;

    @Autowired
    public EquipmentController(PackageEquipments packageEquipments, EquipmentServiceImpl equipmentService, KitService kitService) {
        this.packageEquipments = packageEquipments;
        this.equipmentService = equipmentService;
        this.kitService = kitService;
    }

    @GetMapping("/admin/equipments")
    public String adminEquipmentsPage(Model model) {
        model.addAttribute("activePage", "Equipments");
        model.addAttribute("equipments", equipmentService.findAll());
        return "chief/equipments";
    }

    @GetMapping("/admin/equipment/{id}/edit")
    public String adminEditEquipment(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Equipment");
        model.addAttribute("equipment", equipmentService.findById(id).orElseThrow(NotFoundException::new));
        return "chief/equipment_form";
    }

    @GetMapping("/admin/equipment/create")
    public String adminCreateEquipment(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Equipments"); // TODO ?
        model.addAttribute("equipment", new EquipmentRepr());
        return "chief/equipment_form";
    }

    @PostMapping("/admin/equipment")
    public String adminUpsertEquipment(@Valid EquipmentRepr equipment, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Equipments");

        if (bindingResult.hasErrors()) {
            return "chief/equipment_form";
        }

        equipmentService.save(equipment);
        return "redirect:/admin/equipments";
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
