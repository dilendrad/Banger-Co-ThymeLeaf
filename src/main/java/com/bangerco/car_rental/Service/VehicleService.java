package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.Vehicle;
import com.bangerco.car_rental.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements VehicleServiceInterface {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public void saveVehicleImage(Vehicle vehicle) {

        vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllActiveVehicleImages() {

        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleImageByID(Long id) {

        return vehicleRepository.findById(id);
    }

    public Vehicle getVehicleID(long vehicleID) {

        return vehicleRepository.getByID(vehicleID);
    }

    @Override
    public List<Vehicle> getAllSmallTownCars() {
        return vehicleRepository.getSmallTownCars();
    }
}
