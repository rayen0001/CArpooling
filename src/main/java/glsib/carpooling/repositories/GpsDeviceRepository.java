package glsib.carpooling.repositories;

import glsib.carpooling.entities.GpsDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GpsDeviceRepository extends JpaRepository<GpsDevice, Long> {
    List<GpsDevice> findByVehicleId(Long vehicleId);
}
