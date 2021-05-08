package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Kit;
import ru.optima.repr.KitRepr;
import ru.optima.service.EquipmentServiceImpl;
import ru.optima.service.KitServiceImpl;
import ru.optima.service.UserService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class KitController {

    private KitServiceImpl kitService;
    private UserService userService;
    private PackageEquipments packageEquipments;

    @PostMapping("/equipments_guest/create/kit")
    public String addPackageEquipmentToKit(Principal principal) {
        User user = userService.findByName(principal.getName());
        KitRepr kitRepr = new KitRepr(user, packageEquipments);

        kitService.save(kitRepr);
        return "redirect:/equipments_guest";
    }

    @GetMapping("equipments_guest/save/kit")
    public void saveKit(Model model){
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new KitRepr());
    }
}
