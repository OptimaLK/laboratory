package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.optima.beans.PackageEquipments;
import ru.optima.service.EquipmentServiceImpl;
import ru.optima.util.PathCreator;
import ru.optima.warning.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/package")
public class PackageEquipmentsController {

    private final PackageEquipments packageEquipments;
    private final PathCreator creator;

    @GetMapping({"/", ""})
    public String showPackageEquipments(Model model, SecurityContextHolder auth){
        model.addAttribute("package", packageEquipments);
        return creator.createPath(auth,"package");
    }

//    @GetMapping("/bag/add/{id}")
//    public void addEquipmentToBagById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        packageEquipments.add(equipmentService.findById(id).orElseThrow(NotFoundException::new));
//        response.sendRedirect(request.getHeader("referer"));
//    }
}
