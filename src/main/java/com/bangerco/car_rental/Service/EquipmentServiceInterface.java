package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.Equipment;

import java.util.List;

public interface EquipmentServiceInterface {
    void saveEquipment(Equipment equipment);

    List<Equipment> findAllEquipment();

    Equipment findEquipmentByID(Long equipmentID);

    List<Equipment> getAllEquipment();

    Equipment getEquipmentByID(long id);

    void deleteEquipment(long id);
}
