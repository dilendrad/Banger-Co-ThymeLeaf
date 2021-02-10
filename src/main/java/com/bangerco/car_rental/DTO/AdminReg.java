package com.bangerco.car_rental.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminReg {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String NIC;
    private String username;
    private String password;
    private int age;

}
