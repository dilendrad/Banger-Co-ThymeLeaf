package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter, Integer> {

    @Query("SELECT renter from Renter renter where renter.userID = ?1")
    Renter getByUserID(int userID);

    @Query("SELECT renter from Renter renter where renter.renterID = ?1")
    Renter getById(int id);
}
