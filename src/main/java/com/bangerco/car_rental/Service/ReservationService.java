package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.CalculateCosts.CalculateReservationTotal;
import com.bangerco.car_rental.DTO.ReservationReg;
import com.bangerco.car_rental.Entity.Equipment;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Entity.Reservation;
import com.bangerco.car_rental.Entity.Vehicle;
import com.bangerco.car_rental.Repository.EquipmentRepository;
import com.bangerco.car_rental.Repository.ReservationRepository;
import com.bangerco.car_rental.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements ReservationInterface {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    RenterService renterService;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public boolean saveReservationForRenter(@RequestParam(value = "equipmentID", required = false)
                                                       List<Long> equipmentList, ReservationReg reservationReg) {


        try {

            Vehicle vehicle = vehicleService.getVehicleID(reservationReg.getVehicleID());

            Renter renter = renterService.getRenterByID(reservationReg.getRenterID());

            Reservation reservation = new Reservation();

            Equipment equipment = new Equipment();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate pickUpDate = LocalDate.parse(reservationReg.getPickUpDate(), formatter);
            LocalDate dropOffDate = LocalDate.parse(reservationReg.getDropOffDate(), formatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime pickUpTime = LocalTime.parse(reservationReg.getPickUpTime(), timeFormatter);
            LocalTime dropOffTime = LocalTime.parse(reservationReg.getDropOffTime(), timeFormatter);

            reservation.setPickUpDate(pickUpDate);
            reservation.setDropOffDate(dropOffDate);
            reservation.setPickUpTime(pickUpTime);
            reservation.setDropOffTime(dropOffTime);

            reservation.setStatus("Active");

            reservationReg.setReservation(reservation);

            List<Reservation> getAllVehicleReservations = vehicle.getReservationList();


                    if (CalculateReservationTotal.pickUpDateIsBeforeDropOffDate(reservation) == true) {

                        if (!validateWithExistingVehicle(getAllVehicleReservations, reservationReg.getVehicleID(), reservation)) {

                            List<Reservation> getReservation = new ArrayList<>();

                            List<Equipment> newEquipmentList = new ArrayList<>();

                            calculateEquipmentCost(equipmentList, newEquipmentList);

                            CalculateReservationTotal.calculateCostBetweenDays(reservation);

                            double totalBookingPrice = CalculateReservationTotal.calculateTotalReservation(reservation, vehicle, newEquipmentList);
                            reservation.setTotalCost(totalBookingPrice);

                            getReservation = renter.getReservationList();
                            getReservation.add(reservation);
                            renter.setReservationList(getReservation);

                            reservation.setVehicle(vehicle);
                            reservation.setRenter(renter);
                            reservation.setEquipmentList(newEquipmentList);
                            getReservation = vehicle.getReservationList();
                            getReservation.add(reservation);
                            vehicle.setReservationList(getReservation);
                            reservationRepository.save(reservation);
                            return true;
                        }
                    }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public double calculateEquipmentCost(List<Long> equipmentList, List<Equipment> newEquipmentList) {

        if(equipmentList != null) {
            for (Long newEquipment : equipmentList) {
                newEquipmentList.add(equipmentService.findEquipmentByID(newEquipment));
            }

        }
        double calculateEquipment = CalculateReservationTotal.calculateEquipment(newEquipmentList);

        return calculateEquipment;
    }

    @Override
    public List<Reservation> getMyReservations(int renter_ID) {
        return reservationRepository.getMyReservations(renter_ID);
    }

//    public boolean validateWithExistingReservation(Reservation reservation, List<Reservation> reservationList) {
//
//        final LocalDate initialDate = reservation.getPickUpDate();
//        final LocalDate latterDate = reservation.getDropOffDate();
//
//        for (Reservation r : reservationList) {
//
//            if(!(initialDate.isAfter(r.getDropOffDate())) || latterDate.isBefore(r.getPickUpDate())) {
//
//                return true;
//            }
//
//            else {
//
//                return false;
//            }
//        }
//
//        return false;
//
//    }

    public boolean validateWithExistingVehicle(List<Reservation> reservationList, long vehicleID,Reservation reservation) {

        final LocalDate initialDate = reservation.getPickUpDate();
        final LocalDate latterDate = reservation.getDropOffDate();

        for (Reservation r : reservationList) {

            if(!(initialDate.isAfter(r.getDropOffDate())) || latterDate.isBefore(r.getPickUpDate())) {

                return true;
            }

            else {

                return false;
            }
        }

        return false;
    }

    @Override
    public Reservation getReservationByID(long reservationID) {
        return reservationRepository.getReservationByID(reservationID);
    }


}

