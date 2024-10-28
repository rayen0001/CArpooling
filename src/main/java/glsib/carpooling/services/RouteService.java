package glsib.carpooling.services;

import glsib.carpooling.entities.Route;
import glsib.carpooling.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    // Retrieve all routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Retrieve a route by ID
    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    // Create or update a route
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    // Delete a route by ID
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    // Retrieve routes by vehicle ID
    public List<Route> getRoutesByVehicleId(Long vehicleId) {
        return routeRepository.findByVehicleId(vehicleId);
    }
}
