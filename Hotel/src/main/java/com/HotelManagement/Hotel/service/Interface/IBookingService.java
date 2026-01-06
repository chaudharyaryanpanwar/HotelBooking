package com.HotelManagement.Hotel.service.Interface;

import com.HotelManagement.Hotel.dto.Response;
import com.HotelManagement.Hotel.entity.Booking;

public interface IBookingService {
    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);
    Response getAllBookings();
    Response cancelBooking(Long bookingId);


}
