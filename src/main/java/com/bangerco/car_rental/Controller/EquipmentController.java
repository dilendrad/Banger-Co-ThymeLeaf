package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Entity.Equipment;
import com.bangerco.car_rental.Service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/loadEquipmentForm")
    public String loadEquipmentForm(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "AddEquipment";
    }

    @PostMapping("/addEquipment")
    public String addEquipment(@ModelAttribute("equipment") Equipment equipment) {
        Equipment addEquipment = new Equipment();
        addEquipment.setEquipmentID(equipment.getEquipmentID());
        addEquipment.setEquipmentName(equipment.getEquipmentName());
        addEquipment.setEquipmentDescription(equipment.getEquipmentDescription());
        addEquipment.setPrice(equipment.getPrice());
        equipmentService.saveEquipment(addEquipment);

        return "redirect:/admin/loadAdminHome";
    }

    @GetMapping("/deleteEquipment/{id}")
    public String deleteEquipment(@PathVariable(value = "id")long id)
    {
        Equipment equipment = equipmentService.getByID(id);
        equipmentService.deleteEquipment(equipment.getEquipmentID());

        return "redirect:/admin/loadAdminHome";
    }


}
