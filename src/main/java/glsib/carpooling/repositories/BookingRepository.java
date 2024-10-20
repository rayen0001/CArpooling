package glsib.carpooling.repositories;

import glsib.carpooling.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // You can add custom query methods here if needed
}
