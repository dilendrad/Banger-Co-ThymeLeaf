package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.EmployeeReg;
import com.bangerco.car_rental.Entity.Employee;
import com.bangerco.car_rental.Entity.Renter;

import java.util.List;

public interface EmployeeServiceInterface {
    Employee saveEmployee(EmployeeReg employeeReg);

    void save (Employee employee);

    List<Employee> getAllEmployees();

    Employee getByID(int id);
}
