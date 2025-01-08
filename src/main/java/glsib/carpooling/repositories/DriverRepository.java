package glsib.carpooling.repositories;

import glsib.carpooling.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    // Add custom queries if needed
}

