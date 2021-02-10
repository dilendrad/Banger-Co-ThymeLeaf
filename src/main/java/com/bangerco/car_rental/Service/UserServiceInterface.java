package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserServiceInterface extends UserDetailsService {

    User save (User user);

    User searchByUsername (String username);

    List<User> getAll();

    User findTableByID (int userID);

    void deleteRenter (int userID);

    void blacklistRenter(int id);

    void activeRenter(int id);
}
