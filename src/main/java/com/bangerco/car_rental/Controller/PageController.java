package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Entity.User;
import com.bangerco.car_rental.Service.AdminService;
import com.bangerco.car_rental.Service.RenterService;
import com.bangerco.car_rental.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    @Autowired
    RenterService renterService;

    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String LoadLoginPage() {
        return "Login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        String username;

        //Gets the current username

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchByUsername(username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Admin"))) {
            return "redirect:/admin/loadAdminHome";
        }
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Renter"))) {

            if (user.getAge() > 25 && !user.getStatus().equals("Blacklist")) {

                return "redirect:/renter/loadHome";
            }
            if (user.getAge() < 25 && !user.getStatus().equals("Blacklist")) {

                return "redirect:/renter/loadHomeBelow25";
            } else {

                return "BlacklistPage";
            }
        }

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Employee"))) {
            return "redirect:/employee/loadHome";
        }

        return "Login";
    }
}
