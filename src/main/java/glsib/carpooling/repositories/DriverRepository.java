package glsib.carpooling.repositories;

import glsib.carpooling.entities.Driver;
import glsib.carpooling.entities.Event;
import glsib.carpooling.entities.FuelConsumption;
import glsib.carpooling.entities.GpsLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    // Add custom queries if needed
}

