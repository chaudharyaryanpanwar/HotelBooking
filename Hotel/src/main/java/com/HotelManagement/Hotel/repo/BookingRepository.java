package com.HotelManagement.Hotel.repo;

import com.HotelManagement.Hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

}
