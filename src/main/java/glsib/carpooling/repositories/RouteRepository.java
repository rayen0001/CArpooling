package glsib.carpooling.repositories;

import glsib.carpooling.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByVehicleId(Long vehicleId);}
