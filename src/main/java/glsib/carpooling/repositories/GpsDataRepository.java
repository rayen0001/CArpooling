package glsib.carpooling.repositories;

import glsib.carpooling.entities.GpsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GpsDataRepository extends JpaRepository<GpsData, Long> {
    List<GpsData> findByGpsDeviceId(Long gpsDeviceId);
}
