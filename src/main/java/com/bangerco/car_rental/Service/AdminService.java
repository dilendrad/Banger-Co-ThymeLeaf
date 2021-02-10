package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.AdminReg;
import com.bangerco.car_rental.Entity.Admin;
import com.bangerco.car_rental.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceInterface{

    @Autowired
    AdminRepository adminRepository;


    @Override
    public Admin saveAdmin(AdminReg adminReg) {

        Admin admin = new Admin();
        admin.setFirstName(adminReg.getFirstName());
        admin.setLastName(adminReg.getLastName());
        admin.setAddress(adminReg.getAddress());
        admin.setPhoneNumber(adminReg.getPhoneNumber());
        admin.setNIC(adminReg.getNIC());
        admin.setAge(adminReg.getAge());
        return adminRepository.save(admin);

    }

    @Override
    public void save(Admin admin) {

        adminRepository.save(admin);
    }
}
