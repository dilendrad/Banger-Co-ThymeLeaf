package com.bangerco.car_rental.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReg {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String NIC;
    private String username;
    private String password;
    private int age;
}
