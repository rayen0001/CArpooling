package glsib.carpooling.repositories;

import glsib.carpooling.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    // You can add custom query methods here if needed
}
