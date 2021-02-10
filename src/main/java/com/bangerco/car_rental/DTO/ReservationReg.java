package com.bangerco.car_rental.DTO;

import com.bangerco.car_rental.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationReg {

    private String pickUpDate;
    private String dropOffDate;
    private String pickUpTime;
    private String dropOffTime;
    private String status;
    private String totalPrice;

    private User user;
    private Vehicle vehicle;
    private Renter renter;
    private Equipment equipment;
    private Reservation reservation;

    private List<Equipment> equipmentList;
    private int renterID;
    private long vehicleID;
    private long equipmentID;
    private long reservationID;
}
