package com.bangerco.car_rental.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String vehicleBrand;
    private String vehicleModel;
    private String categoryType;
    private String transmissionType;
    private String fuelType;
    private String airConditioning;
    private int doors;
    private int seats;
    private double categoryPrice;

    @Lob
    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Reservation.class)
    private List<Reservation> reservationList;

    @Override
    public String toString() {
        return "Car [carID = " + id +  "brand = " + vehicleBrand + "model = " + vehicleModel + "transmissionType = " + transmissionType + "airConditioning = "
                + airConditioning +  "seats = " + seats + "doors = " + doors + "image =  + Arrays.toString(image)" + "]";
    }

}
