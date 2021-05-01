package ru.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.optima.beans.PackageEquipments;
import ru.optima.service.EquipmentServiceImpl;
import ru.optima.warning.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PackageEquipmentsController {

    private PackageEquipments packageEquipments;
    private EquipmentServiceImpl equipmentService;

    @Autowired
    public PackageEquipmentsController(PackageEquipments packageEquipments, EquipmentServiceImpl service) {
        this.packageEquipments = packageEquipments;
        this.equipmentService = service;
    }

    @GetMapping("/package")
    public String showPackageEquipments(Model model){
        model.addAttribute("package", packageEquipments);
        return "package";
    }

//    @GetMapping("/bag/add/{id}")
//    public void addEquipmentToBagById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        packageEquipments.add(equipmentService.findById(id).orElseThrow(NotFoundException::new));
//        response.sendRedirect(request.getHeader("referer"));
//    }
}
