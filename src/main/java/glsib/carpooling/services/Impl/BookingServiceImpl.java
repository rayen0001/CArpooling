package glsib.carpooling.services.Impl;

import glsib.carpooling.entities.Booking;
import glsib.carpooling.repositories.BookingRepository;
import glsib.carpooling.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
    @Override
    public void cancelBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        booking.ifPresent(b -> {
            // Assuming you have a field to represent booking status
            b.setStatus("Cancelled"); // Change the status to "Cancelled"
            bookingRepository.save(b); // Save the updated booking
        });
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getUser().getId() == userId)
                .toList();
    }
}
