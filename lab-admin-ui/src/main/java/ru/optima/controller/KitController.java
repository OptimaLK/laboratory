package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Kit;
import ru.optima.repr.KitRepr;
import ru.optima.service.EquipmentServiceImpl;
import ru.optima.service.KitServiceImpl;
import ru.optima.service.UserService;
import ru.optima.util.PathCreator;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/kit")
public class KitController {

    private final PathCreator pathCreator;
    private final KitServiceImpl kitService;
    private final UserService userService;
    private final PackageEquipments packageEquipments;

    @PostMapping("/create")
    public String addPackageEquipmentToKit(Principal principal, SecurityContextHolder auth) {
        User user = userService.findByName(principal.getName());
        KitRepr kitRepr = new KitRepr(user, packageEquipments);

        kitService.save(kitRepr);
        return pathCreator.createPath(auth, "redirect:" + "equipments_guest");
    }

    @GetMapping("/save")
    public void saveKit(Model model){
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new KitRepr());
    }
}
