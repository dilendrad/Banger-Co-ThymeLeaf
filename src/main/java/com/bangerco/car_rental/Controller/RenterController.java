package com.bangerco.car_rental.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/renter")
public class RenterController {

    @GetMapping("/loadHome")
    public String loadHome(Model model)
    {
        return "RenterHome";
    }

    @GetMapping("/loadHomeBelow25")
    public String loadHomeBelow25(Model model)
    {
        return "RenterHomeBelow25";
    }
}
