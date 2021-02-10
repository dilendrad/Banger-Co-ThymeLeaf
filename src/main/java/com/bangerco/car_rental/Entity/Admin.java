package com.bangerco.car_rental.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int adminID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String NIC;
    private int age;
    private int userID;
}
