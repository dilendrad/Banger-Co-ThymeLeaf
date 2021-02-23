package com.bangerco.car_rental.DTO;

import com.bangerco.car_rental.Entity.Renter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentReg {

    private String documentName;
    private String documentType;

    private Renter renter;
}
