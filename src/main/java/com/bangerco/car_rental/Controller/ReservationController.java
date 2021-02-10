package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.CalculateCosts.CalculateReservationTotal;
import com.bangerco.car_rental.DTO.ReservationReg;
import com.bangerco.car_rental.Entity.*;
import com.bangerco.car_rental.Repository.ReservationRepository;
import com.bangerco.car_rental.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    UserService userService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    RenterService renterService;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/loadRenterHome")
    public String loadHome(Model model)
    {
        return "RenterHome";
    }

    @GetMapping("/loadRenterUnder25Home")
    public String loadHomeUnder25(Model model)
    {
        return "RenterHomeBelow25";
    }

    @GetMapping("/createReservation/{id}")
    public String createReservation(@PathVariable(value = "id") int vehicleID, Model model) {

        try {

            Vehicle vehicle = vehicleService.getVehicleID(vehicleID);

            List<Equipment> equipmentList = equipmentService.findAllEquipment();

            String username = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            User user = userService.searchByUsername(username);
            Renter renter = renterService.getRenterByUserID(user.getUserID());

            if(vehicle != null) {

                ReservationReg reservationReg = new ReservationReg();

                reservationReg.setVehicleID(vehicle.getId());
                reservationReg.setVehicle(vehicle);
                reservationReg.setRenterID(renter.getRenterID());
                reservationReg.setRenter(renter);

                model.addAttribute("reservation", reservationReg);
                model.addAttribute("equipmentList", equipmentList);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "AddReservation";
    }

    @PostMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") ReservationReg reservationReg,
                              @RequestParam(value = "equipmentID", required = false) List<Long> equipmentList) {

        reservationService.saveReservationForRenter(equipmentList, reservationReg);

        if(CalculateReservationTotal.pickUpDateIsBeforeDropOffDate(reservationReg.getReservation()) == true) {
            List<Equipment> newEquipmentList = new ArrayList<>();
            showEquipment(equipmentList, newEquipmentList);

            return "redirect:/renter/loadHome";
        } else {
            return "BookingError";
        }

    }

    @GetMapping("/getMyReservations")
    public String getMyReservations(Model model)
    {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchByUsername(username);
        Renter renter = renterService.getRenterByUserID(user.getUserID());


        List<Reservation> reservationList = renter.getReservationList();
        model.addAttribute("reservations",reservationList);
        return "MyReservations";
    }

    @GetMapping("/equipmentCalculation")
    @ResponseBody
    public String showEquipment(@RequestParam List<Long> equipmentList, List<Equipment> newEquipmentList){

        reservationService.calculateEquipmentCost(equipmentList, newEquipmentList);
        return "ConfirmBooking";

    }

    @GetMapping("/extendReservation/{id}")
    public String extendReservation(@PathVariable(value = "id") long reservationID, Model model) {

        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.searchByUsername(username);
        Renter renter = renterService.getRenterByUserID(user.getUserID());

        boolean value = renter.getReservationList().size() > 3;
        if (value == true) {

            Reservation reservation = reservationService.getReservationByID(reservationID);
            reservation.setStatus("Extended");
            reservation.setDropOffTime(LocalTime.of(21, 00, 00, 00));
            reservationRepository.save(reservation);

            if (reservation != null) {
                ReservationReg reservationReg = new ReservationReg();

                reservationReg.setRenterID(renter.getRenterID());
                reservationReg.setRenter(renter);

                model.addAttribute("reservationRegistration", reservationReg);
                model.addAttribute("reservation", reservation);
                return "redirect:/reservation/getMyReservations?success";
            }
        } else {

            return "redirect:/reservation/getMyReservations?failed";
        }

        return "ExtendReservation";
    }

//    @PostMapping("/extendCurrentReservation")
//    public String extendCurrentReservation(@ModelAttribute("reservationRegistration") Reservation reservation) {
//        Reservation extendReservation = reservationService.getReservationByID(reservation.getReservationID());
//
//        if(reservation.equals("true")) {
//            return "redirect:/reservation/getMyReservations?success";
//        } else {
//            return "redirect:/reservation/getMyReservations?failed";
//        }


//    }

}
