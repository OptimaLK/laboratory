package ru.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.persist.repo.EquipmentRepository;

@Controller
public class SuperController {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public SuperController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }


    @RequestMapping("superuser")
    public String superPage(Model model) {
        model.addAttribute("equipments", equipmentRepository.findAll());
        return "superuser";
    }
}
