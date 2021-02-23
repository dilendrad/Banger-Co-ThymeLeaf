package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Entity.License;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Entity.Reservation;
import com.bangerco.car_rental.Entity.User;
import com.bangerco.car_rental.Repository.RenterRepository;
import com.bangerco.car_rental.Repository.ReservationRepository;
import com.bangerco.car_rental.Repository.UserRepository;
import com.bangerco.car_rental.Service.LicenseService;
import com.bangerco.car_rental.Service.RenterService;
import com.bangerco.car_rental.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/license")
public class LicenseController {

    @Autowired
    RenterService renterService;

    @Autowired
    LicenseService licenseService;

    @Autowired
    UserService userService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RenterRepository renterRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/validateLicense/{id}")
    public String validateLicense(@PathVariable(value = "id") int renterID) {

        Renter renter = renterService.getRenterByID(renterID);
        List<License> licenseList = licenseService.validateLicenseData();
        List<Reservation> reservationList = renter.getReservationList();

        for (License license : licenseList) {
            if (renter.getLicenseNo().equals(license.getLicenseNo())) {
                renter.setStatus("Blacklist");
                User user = userService.findTableByID(renter.getUserID());
                user.setStatus("Blacklist");
                for (Reservation reservation : reservationList) {

                    reservation.setStatus("Cancelled");
                    reservationRepository.save(reservation);
                }
                renterRepository.save(renter);
                userRepository.save(user);

            }
        }

        return "redirect:/admin/listRenters";
    }
}

