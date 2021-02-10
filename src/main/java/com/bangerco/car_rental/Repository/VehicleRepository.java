package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT vehicle from Vehicle vehicle where vehicle.id = ?1")
    Vehicle getByID(long id);

    @Query("SELECT vehicle from Vehicle vehicle where vehicle.categoryType = 'Small Town Car'")
    List<Vehicle> getSmallTownCars();


}
