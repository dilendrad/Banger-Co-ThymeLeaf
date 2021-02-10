package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.AdminReg;
import com.bangerco.car_rental.Entity.Admin;

public interface AdminServiceInterface {

    void save(Admin savedAdmin);

    Admin saveAdmin (AdminReg savedAdmin);
}
