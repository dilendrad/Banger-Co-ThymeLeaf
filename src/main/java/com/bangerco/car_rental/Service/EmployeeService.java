package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.DTO.EmployeeReg;
import com.bangerco.car_rental.Entity.Employee;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(EmployeeReg employeeReg) {

        Employee employee = new Employee();
        employee.setFirstName(employeeReg.getFirstName());
        employee.setLastName(employeeReg.getLastName());
        employee.setAddress(employeeReg.getAddress());
        employee.setPhoneNumber(employeeReg.getPhoneNumber());
        employee.setEmail(employeeReg.getEmail());
        employee.setNIC(employeeReg.getNIC());
        employee.setAge(employeeReg.getAge());
        return employeeRepository.save(employee);
    }

    @Override
    public void save (Employee employee) {

        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }

    @Override
    public Employee getByID(int id) {
        return employeeRepository.getOne(id);
    }
}
