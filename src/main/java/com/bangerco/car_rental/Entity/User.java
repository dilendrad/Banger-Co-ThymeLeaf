package com.bangerco.car_rental.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userID;
    private int tableID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int age;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(
                    name = "user_ID", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_ID", referencedColumnName = "roleID"))
    private Collection<Role> roles;
}
