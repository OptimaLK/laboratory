package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.service.BagService;
import ru.optima.service.EquipmentService;
import ru.optima.service.UserService;
import ru.optima.util.PathCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/bag")
public class BagController {

    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private BagService bagService;

    private final PathCreator pathCreator;

    @GetMapping({"","/"})
    public String equipmentsPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Bags");
        model.addAttribute("bags", equipmentService.findAll());
        return pathCreator.createPath(auth, "bags");
    }

    @PostMapping("/take/{equipmentId}")
    public void addEquipmentToBagById(@PathVariable Long equipmentId, Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Equipment equipment = equipmentService.findByEId(equipmentId);
        User user = userService.findByName(principal.getName());
        bagService.addEquipmentToBag(equipment, user);
        bagService.presenceInBag(equipment);
        response.sendRedirect(request.getHeader("referer"));
    }

    @PostMapping("/get/{equipmentId}")
    public void deleteEquipmentToBagById(@PathVariable Long equipmentId, Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Equipment equipment = equipmentService.findByEId(equipmentId);
        User user = userService.findByName(principal.getName());
        bagService.deleteEquipmentToBag(equipment, user);
        bagService.presenceInBag(equipment);
        response.sendRedirect(request.getHeader("referer"));
    }
}
