package com.bangerco.car_rental.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenterReg {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String NIC;
    private String licenseNo;
    private String status;
    private int age;
    private String username;
    private String password;
}
