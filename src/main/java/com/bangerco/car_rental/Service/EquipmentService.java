package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.Equipment;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements EquipmentServiceInterface {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public void saveEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> findAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findEquipmentByID(Long equipmentID) {
        return equipmentRepository.findById(equipmentID).orElse(null);
    }

    @Override
    public List<Equipment> getAllEquipment() {

        return equipmentRepository.findAll();

    }
    public Equipment getByID(long id) {
        return equipmentRepository.getOne(id);
    }

    @Override
    public Equipment getEquipmentByID(long id) {

        return equipmentRepository.getOne(id);
    }

    @Override
    public void deleteEquipment(long id) {

        equipmentRepository.deleteById(id);
    }
}
