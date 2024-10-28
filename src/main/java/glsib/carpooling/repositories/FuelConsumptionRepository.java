package glsib.carpooling.repositories;

import glsib.carpooling.entities.FuelConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelConsumptionRepository extends JpaRepository<FuelConsumption, Long> {
    List<FuelConsumption> findByVehicleId(Long vehicleId);
}
