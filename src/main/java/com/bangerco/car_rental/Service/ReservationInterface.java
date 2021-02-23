package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.ReservationReg;
import com.bangerco.car_rental.Entity.Equipment;
import com.bangerco.car_rental.Entity.Reservation;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReservationInterface {
    List<Reservation> getAllReservations();

    boolean saveReservationForRenter(@RequestParam(value = "equipmentID", required = false)
                                            List<Long> equipmentList, ReservationReg reservationReg);

    double calculateEquipmentCost(List<Long> equipmentList, List<Equipment> newEquipmentList);

    List<Reservation> getMyReservations(int renter_ID);

    Reservation getReservationByID(long reservationID);
}
