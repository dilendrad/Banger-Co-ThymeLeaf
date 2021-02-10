package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Entity.Employee;
import com.bangerco.car_rental.Entity.Equipment;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Entity.User;
import com.bangerco.car_rental.Service.EmployeeService;
import com.bangerco.car_rental.Service.EquipmentService;
import com.bangerco.car_rental.Service.RenterService;
import com.bangerco.car_rental.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RenterService renterService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/loadAdminHome")
    public String loadHome(Model model)
    {
        return "AdminHome";
    }

    @GetMapping("/listRenters")
    public String viewRenters(Model model)
    {
        model.addAttribute("renters", renterService.getAllRenters());

        return "ManageRenters"; //Refers to the HTML page
    }

    @GetMapping("/editRenter/{id}")
    public String showRenterFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        Renter renter = renterService.getByID(id);
        model.addAttribute("renter",renter);

        return "EditRenter";
    }

    @PostMapping("/updateRenter")
    public String saveRenter(@ModelAttribute("renter") Renter renter)
    {

        User user = userService.findTableByID(renter.getUserID());
        user.setFirstName(renter.getFirstName());
        user.setLastName(renter.getLastName());
        userService.save(user);
        renterService.save(renter);

        return "redirect:/admin/listRenters?success";
    }

    @GetMapping("/listEmployees")
    public String viewEmployees(Model model)
    {
        model.addAttribute("employees", employeeService.getAllEmployees());

        return "ManageEmployees"; //Refers to the HTML page
    }

    @GetMapping("/editEmployee/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        Employee employee = employeeService.getByID(id);
        model.addAttribute("employee",employee);

        return "EditEmployee";
    }

    @PostMapping("/updateEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {

        User user = userService.findTableByID(employee.getUserID());
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        userService.save(user);
        employeeService.save(employee);

        return "redirect:/admin/listEmployees?success";
    }

    @GetMapping("/listEquipment")
    public String viewEquipment(Model model)
    {
        model.addAttribute("equipments", equipmentService.getAllEquipment());

        return "ManageEquipment"; //Refers to the HTML page
    }

    @GetMapping("/editEquipment/{id}")
    public String showEquipmentFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        Equipment equipment = equipmentService.getEquipmentByID(id);
        model.addAttribute("equipment",equipment);

        return "EditEquipment";
    }

    @PostMapping("/updateEquipment")
    public String saveEquipment(@ModelAttribute("equipment") Equipment newEquipment)
    {

        Equipment equipment = equipmentService.getEquipmentByID(newEquipment.getEquipmentID());
        equipment.setEquipmentName(newEquipment.getEquipmentName());
        equipment.setEquipmentDescription(newEquipment.getEquipmentDescription());
        equipment.setPrice(newEquipment.getPrice());
        equipmentService.saveEquipment(equipment);

        return "AdminHome";
    }


}
