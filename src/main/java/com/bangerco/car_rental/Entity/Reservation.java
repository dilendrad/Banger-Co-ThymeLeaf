package com.bangerco.car_rental.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationID;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dropOffDate;

    private LocalTime pickUpTime;
    private LocalTime dropOffTime;

    private String status;

    private double totalCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_vehicleID")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_renterID")
    private Renter renter;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "reservation_equipmentID",
            joinColumns = @JoinColumn(name = "reservationID"),
            inverseJoinColumns = @JoinColumn(name = "equipmentID"))
    private List<Equipment> equipmentList;


}
