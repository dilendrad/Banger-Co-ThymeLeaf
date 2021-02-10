package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface VehicleServiceInterface {

    void saveVehicleImage(Vehicle vehicle);

    List<Vehicle> getAllActiveVehicleImages();

    Optional<Vehicle> getVehicleImageByID(Long id);

    List<Vehicle> getAllSmallTownCars();
}
