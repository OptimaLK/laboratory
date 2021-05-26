package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.XmlException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.VerificationRepr;
import ru.optima.service.EquipmentServiceImpl;
import ru.optima.service.VerificationService;
import ru.optima.util.AppForVerification;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/verification")
public class VerificationController {

    private final PathCreator pathCreator;
    private final EquipmentServiceImpl equipmentService;
    private final VerificationService verificationService;
    private VerificationRepr verificationRepr;

    @GetMapping("/app_verification/{id}")
    public String appVerification(Model model, SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("equipment", equipmentService.findById(id).orElseThrow(NotFoundException ::new));
        return pathCreator.createPath(auth, "app_verification");
    }

    @PostMapping("/save")
    public String appVerificationWord(@ModelAttribute("equipment") Equipment equipment, @RequestParam(value = "add") String add,
                                      @RequestParam(value = "customer") String customer, @RequestParam(value = "person") String person,
                                      @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email,
                                      @RequestParam(value = "number") String number, @RequestParam(value = "date") String date,
                                      @RequestParam(value = "enterprise") String enterprise,
                                      @RequestParam(value = "name") String name, @RequestParam(value = "type_eq") String type_eq,
                                      @RequestParam(value = "sum") String sum, @RequestParam(value = "number_reg") String number_reg,
                                      @RequestParam(value = "factoryNumber") String factoryNumber, @RequestParam(value = "urgency") boolean urgency,
                                      @RequestParam(value = "protocol") boolean protocol, @RequestParam(value = "certificate") boolean certificate,
                                      @RequestParam(value = "cal_uncertainty") boolean cal_uncertainty, @RequestParam(value = "note") String note) throws IOException, XmlException {

        verificationRepr = new VerificationRepr(customer, person, phone, email, number, date, enterprise, name,
                type_eq, sum, number_reg, factoryNumber, urgency, protocol, certificate, cal_uncertainty, note);
        verificationService.addEquipmentToVerification(verificationRepr);

        if(add.equals("true"))
            equipment.setActiv(Boolean.parseBoolean(add));
        else {
            new AppForVerification(verificationService.getEquipmentToVerification());
            equipment.setActiv(true);
        }
        return "redirect:/equipment";
    }
}
