package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.DTO.AdminReg;
import com.bangerco.car_rental.DTO.EmployeeReg;
import com.bangerco.car_rental.DTO.RenterReg;
import com.bangerco.car_rental.Entity.*;
import com.bangerco.car_rental.Service.AdminService;
import com.bangerco.car_rental.Service.EmployeeService;
import com.bangerco.car_rental.Service.RenterService;
import com.bangerco.car_rental.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    RenterService renterService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registerAdmin")
    public String loadAdminRegister(Model model) {

        model.addAttribute("admin", new AdminReg());

        return "RegisterAdmin";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin") AdminReg adminReg) {

        Admin savedAdmin = adminService.saveAdmin(adminReg);
        User user = new User();
        user.setTableID(savedAdmin.getAdminID());
        user.setFirstName(adminReg.getFirstName());
        user.setLastName(adminReg.getLastName());
        user.setUsername(adminReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(adminReg.getPassword()));
        user.setStatus("Active");
        user.setAge(adminReg.getAge());
        user.setRoles(Arrays.asList(new Role("Admin")));
        User savedUser = userService.save(user);
        savedAdmin.setUserID(savedUser.getUserID());
        adminService.save(savedAdmin);

        return "redirect:/user/registerAdmin?success";
    }

    @GetMapping("/registerRenter")
    public String loadRenterRegister(Model model) {

        model.addAttribute("renter", new RenterReg());

        return "RegisterRenter";
    }

    @PostMapping("/saveRenter")
    public String saveRenter(@ModelAttribute("renter") RenterReg renterReg) {

        Renter savedRenter = renterService.saveRenter(renterReg);
        User user = new User();
        user.setTableID(savedRenter.getRenterID());
        user.setFirstName(renterReg.getFirstName());
        user.setLastName(renterReg.getLastName());
        user.setUsername(renterReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(renterReg.getPassword()));
        user.setStatus("Active");
        savedRenter.setStatus("Active");
        savedRenter.setLicenseNo(renterReg.getLicenseNo());
        user.setAge(renterReg.getAge());
        user.setRoles(Arrays.asList(new Role("Renter")));
        User savedUser = userService.save(user);
        savedRenter.setUserID(savedUser.getUserID());
        renterService.save(savedRenter);

        return "redirect:/user/registerRenter?success";
    }

    @GetMapping("/registerEmployee")
    public String loadEmployeeRegister(Model model) {

        model.addAttribute("employee", new EmployeeReg());

        return "RegisterEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") EmployeeReg employeeReg) {

        Employee savedEmployee = employeeService.saveEmployee(employeeReg);
        User user = new User();
        user.setTableID(savedEmployee.getEmployeeID());
        user.setFirstName(employeeReg.getFirstName());
        user.setLastName(employeeReg.getLastName());
        user.setUsername(employeeReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(employeeReg.getPassword()));
        user.setStatus("Active");
        user.setAge(employeeReg.getAge());
        user.setRoles(Arrays.asList(new Role("Employee")));
        User savedUser = userService.save(user);
        savedEmployee.setUserID(savedUser.getUserID());
        employeeService.save(savedEmployee);

        return "redirect:/user/registerEmployee?success";
    }
}
