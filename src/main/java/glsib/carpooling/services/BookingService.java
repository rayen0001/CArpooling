package glsib.carpooling.services;

import glsib.carpooling.entities.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    List<Booking> findAll();
    Booking getBookingById(Long id);
    void deleteBooking(Long id);
    List<Booking> getBookingsByUserId(Long userId);

    void cancelBooking(Long id);
}
