package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.UserRepository;
import ru.optima.repr.UserRepr;
import ru.optima.service.BagService;
import ru.optima.service.EquipmentService;
import ru.optima.service.UserService;
import ru.optima.warning.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @PostMapping("/take/{equipmentId}")
    public void addEquipmentToBagById(@PathVariable Long equipmentId, Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Equipment equipment = equipmentService.findByEId(equipmentId);
        User user = userService.findByName(principal.getName());
        bagService.addEquipmentToBag(equipment, user);
        equipment.setActivity(false);
        response.sendRedirect(request.getHeader("referer"));
    }

}
