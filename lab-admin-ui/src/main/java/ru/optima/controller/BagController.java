package ru.optima.controller;

import liquibase.pro.packaged.B;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.BagRepr;
import ru.optima.service.BagService;
import ru.optima.service.EquipmentService;
import ru.optima.service.UserService;
import ru.optima.service.WorkService;
import ru.optima.util.PathCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private PathCreator pathCreator;
    @Autowired
    private WorkService workService;

    @GetMapping({"","/"})
    public String equipmentsPage(Model model, Principal principal, SecurityContextHolder auth) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("activePage", "Bag");
        model.addAttribute("equipmentsInLastBag", bagService.findAllEquipments(user));
        model.addAttribute("sel", true);
        List<Bag> bagList = new ArrayList <>();
        for(Bag value : user.getBags()) {
            if(value.getWork() != null) {
                bagList.add(value);
            }
        }
        model.addAttribute("bagAll", bagList);
        return pathCreator.createPath(auth, "bag");
    }

    @GetMapping("/select/{id}")
    public String selectBag(@PathVariable Long id, Model model, Principal principal, SecurityContextHolder auth) throws IOException {
        User user = userService.findByName(principal.getName());
        model.addAttribute("equipmentsInLastBag", bagService.selectBag(id, user));
        model.addAttribute("sel", false);
        return pathCreator.createPath(auth, "bag");
    }

    @PostMapping("/take/{equipmentId}")
    public void addEquipmentToBagById(@PathVariable Long equipmentId, Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Equipment equipment = equipmentService.findByEId(equipmentId);
        User user = userService.findByName(principal.getName());

        bagService.addEquipmentToBag(equipment, user);
        response.sendRedirect(request.getHeader("referer"));
    }

    @PostMapping("/take")
    public void addBagById(Principal principal, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.findByName(principal.getName());
        bagService.addBag(user);
        response.sendRedirect(request.getHeader("referer"));
    }

    @PostMapping("/get/{equipmentId}")
    public void deleteEquipmentToBagById(@PathVariable Long equipmentId, Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Equipment equipment = equipmentService.findByEId(equipmentId);
        User user = userService.findByName(principal.getName());
        bagService.deleteEquipmentToBag(equipment, user);
        response.sendRedirect(request.getHeader("referer"));
    }

    @DeleteMapping("/delete")
    public String deleteEquipmentsInLastBag(Principal principal, SecurityContextHolder auth) {
        User user = userService.findByName(principal.getName());
        bagService.deleteAllEquipmentsInBag(user);
        return "redirect:/bag";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBag(@PathVariable Long id, Principal principal, SecurityContextHolder auth) {
        User user = userService.findByName(principal.getName());
        bagService.deleteBagById(user, id);
        return "redirect:/bag";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/bag";
    }

    @GetMapping("/form")
    public String editUser(Model model, SecurityContextHolder auth, Principal principal) {
        User user = userService.findByName(principal.getName());
        List<Equipment> equipments = bagService.findAllEquipmentsFirstBag(user);
        BagRepr bagRepr = bagService.createBagReprAndAddUserAndEquipments(user, equipments);
        model.addAttribute("activePage", "Bag");
        model.addAttribute("bag", bagRepr);
        switch (pathCreator.getRole(auth)) {
            case "chief": {
                model.addAttribute("work", workService.findAll());
                break;
            }
            case "executor": {
                String userLogin = principal.getName();
                Long userId = userService.findByName(userLogin).getId();
                model.addAttribute("work", workService.findAllWorksByUserIdWithStatusName(userId));
                break;
            }
            default: {
                model.addAttribute("work", workService.findAll());
            }
        }
        return pathCreator.createPath(auth, "bag_form");
    }

    @PostMapping({"/registration"})
    public String editUser(@ModelAttribute BagRepr bagRepr, Principal principal, SecurityContextHolder auth) {
        bagService.createNewBagAndSaveOldBag(bagRepr, userService.findByName(principal.getName()));
        bagService.addBag(userService.findByName(principal.getName()));
        return "redirect:/bag"; //TODO Добавить ссылку на историю сумок bag_history
    }
}
