package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.RenterReg;
import com.bangerco.car_rental.Entity.Employee;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService implements RenterServiceInterface {

    @Autowired
    RenterRepository renterRepository;

    @Override
    public Renter saveRenter(RenterReg renterReg) {

        Renter renter = new Renter();
        renter.setFirstName(renterReg.getFirstName());
        renter.setLastName(renterReg.getLastName());
        renter.setAddress(renterReg.getAddress());
        renter.setPhoneNumber(renterReg.getPhoneNumber());
        renter.setEmail(renterReg.getEmail());
        renter.setLicenseNo(renterReg.getNIC());
        renter.setStatus("Active");
        renter.setAge(renterReg.getAge());
        return renterRepository.save(renter);
    }

    @Override
    public void save (Renter renter) {

        renterRepository.save(renter);
    }

    @Override
    public List<Renter> getAllRenters() {

        return renterRepository.findAll();

    }

    public Renter getByID(int id) {
        return renterRepository.getOne(id);
    }

    @Override
    public Renter getRenterByUserID(int userID) {
        return renterRepository.getByUserID(userID);
    }

    public Renter getRenterByID(int renterID) {

        return renterRepository.getById(renterID);
    }


}
