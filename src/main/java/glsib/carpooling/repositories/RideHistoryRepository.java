package glsib.carpooling.repositories;

import glsib.carpooling.entities.RideHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideHistoryRepository extends JpaRepository<RideHistory, Long> {
    // You can add custom query methods here if needed
}
