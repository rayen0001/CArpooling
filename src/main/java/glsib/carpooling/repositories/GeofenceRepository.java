package glsib.carpooling.repositories;

import glsib.carpooling.entities.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeofenceRepository extends JpaRepository<Geofence, Long> {
    List<Geofence> findByVehicles_Id(Long vehicleId);
}
