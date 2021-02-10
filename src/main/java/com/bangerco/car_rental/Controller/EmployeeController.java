package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Service.RenterService;
import com.bangerco.car_rental.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    UserService userService;

    @Autowired
    RenterService renterService;

    @GetMapping("/loadHome")
    public String loadHome(Model model)
    {
        return "EmployeeHome";
    }

    @GetMapping("/listRenters")
    public String viewRenters(Model model)
    {
        model.addAttribute("renters", renterService.getAllRenters());

        return "ManageRenters";
    }

    @GetMapping("/blacklistRenter/{id}")
    public String blacklistRenter(@PathVariable(value = "id")int id)
    {
        userService.blacklistRenter(id);
        return "redirect:/employee/listRenters";
    }

    @GetMapping("/activeRenter/{id}")
    public String activeRenter(@PathVariable(value = "id")int id)
    {
        userService.activeRenter(id);
        return "redirect:/employee/listRenters";
    }
}
