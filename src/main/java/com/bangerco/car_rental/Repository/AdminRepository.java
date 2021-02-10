package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Integer> {
}
