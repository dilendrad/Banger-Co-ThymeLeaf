package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository <Document, Integer> {


}
