package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer> {
}
