package com.bangerco.car_rental.CalculateCosts;

import com.bangerco.car_rental.Entity.Equipment;
import com.bangerco.car_rental.Entity.Reservation;
import com.bangerco.car_rental.Entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalculateReservationTotal {

    public static Long calculateCostBetweenDays (Reservation reservation) {

        LocalDate startLocalDate = reservation.getPickUpDate();
        LocalDate endLocalDate = reservation.getDropOffDate();

        long differenceBetweenDays = DAYS.between(startLocalDate, endLocalDate);

        return differenceBetweenDays;
    }

    public static double calculateEquipment(List<Equipment> equipmentList) {

        double totalEquipmentPrice = 0.0;

        for(Equipment equipment : equipmentList) {

            totalEquipmentPrice += equipment.getPrice();
        }

        return totalEquipmentPrice;
    }

    public static double calculateVehiclePrice(Reservation reservation, Vehicle vehicle) {

        double totalVehiclePrice = vehicle.getCategoryPrice();
        long numberOfDays = calculateCostBetweenDays(reservation);

        return (totalVehiclePrice * numberOfDays);
    }

    public static double calculateTotalReservation(Reservation reservation, Vehicle vehicle, List<Equipment> equipmentList) {
        double totalCost = calculateEquipment(equipmentList) + calculateVehiclePrice(reservation, vehicle);

        return totalCost;
    }

    public static boolean pickUpDateIsBeforeDropOffDate(Reservation reservation) {

        if(reservation.getPickUpDate().compareTo(reservation.getDropOffDate()) > 0) {
            return false;

        } else {
            return true;
        }
    }

}
