package com.bangerco.car_rental.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "renter")
public class Renter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int renterID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String licenseNo;
    private String status;
    private int age;
    private int userID;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Reservation.class)
    private List<Reservation> reservationList;
}
