package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {

    @Query("SELECT u from User u where u.username = ?1")
    User searchUsername(String username);

    @Query("SELECT u from User u where u.tableID = ?1")
    User getByTableID(int id);
}
