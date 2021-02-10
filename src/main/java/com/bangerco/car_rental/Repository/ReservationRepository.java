package com.bangerco.car_rental.Repository;

import com.bangerco.car_rental.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT reservation from Reservation reservation where reservation.renter.renterID = ?1")
    List<Reservation> getMyReservations(int customer_ID);

    @Query("SELECT reservation from Reservation reservation where reservation.reservationID = ?1")
    Reservation getReservationByID(long reservationID);
}
