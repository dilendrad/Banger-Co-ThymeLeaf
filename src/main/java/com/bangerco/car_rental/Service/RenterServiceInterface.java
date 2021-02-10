package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.RenterReg;
import com.bangerco.car_rental.Entity.Renter;

import java.util.List;

public interface RenterServiceInterface {


    Renter saveRenter(RenterReg renterReg);

    void save(Renter renter);

    List<Renter> getAllRenters();

    Renter getRenterByUserID(int userID);
}
