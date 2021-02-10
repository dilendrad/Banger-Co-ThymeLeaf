package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.Role;
import com.bangerco.car_rental.Entity.User;
import com.bangerco.car_rental.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User searchByUsername(String username) {
        return userRepository.searchUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findTableByID(int userID) {
        return userRepository.getOne(userID);
    }

    @Override
    public void deleteRenter(int userID) {

        userRepository.deleteById(userID);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.searchUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public void blacklistRenter(int id)
    {
        User user = userRepository.getOne(id);
        user.setStatus("Blacklist");
        userRepository.save(user);
    }

    @Override
    public void activeRenter(int id)
    {
        User user = userRepository.getOne(id);
        user.setStatus("Active");
        userRepository.save(user);
    }
}
