package glsib.carpooling.repositories;

import glsib.carpooling.entities.GpsIo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GpsIoRepository extends JpaRepository<GpsIo, Long> {
    List<GpsIo> findByStatus(String status);
}
