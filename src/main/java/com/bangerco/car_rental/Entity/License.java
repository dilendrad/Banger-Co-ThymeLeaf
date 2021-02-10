package com.bangerco.car_rental.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class License {

    private int licenseID;
    private String name;
    private String licenseNo;
    private String status;
}
