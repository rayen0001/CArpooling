package glsib.carpooling.repositories;

import glsib.carpooling.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    // You can add custom query methods here if needed
}
