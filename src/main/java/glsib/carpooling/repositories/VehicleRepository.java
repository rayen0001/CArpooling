package glsib.carpooling.repositories;

import glsib.carpooling.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByStatus(String status);
    List<Vehicle> findByRegistrationNumber(String registrationNumber);
}
