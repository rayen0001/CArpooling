package glsib.carpooling.services;

import glsib.carpooling.entities.Geofence;
import glsib.carpooling.entities.Vehicle;
import glsib.carpooling.repositories.GeofenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeofenceService {

    @Autowired
    private GeofenceRepository geofenceRepository;

    // Retrieve all geofences
    public List<Geofence> getAllGeofences() {
        return geofenceRepository.findAll();
    }

    // Retrieve a geofence by ID
    public Geofence getGeofenceById(Long id) {
        return geofenceRepository.findById(id).orElse(null);
    }

    // Create or update a geofence
    public Geofence saveGeofence(Geofence geofence) {
        return geofenceRepository.save(geofence);
    }

    // Delete a geofence by ID
    public void deleteGeofence(Long id) {
        geofenceRepository.deleteById(id);
    }

    // Retrieve geofences by vehicle ID
    public List<Geofence> getGeofencesByVehicleId(Long vehicleId) {
        return geofenceRepository.findByVehicles_Id(vehicleId);
    }

    // Add a vehicle to a geofence
    public void addVehicleToGeofence(Long geofenceId, Vehicle vehicle) {
        Geofence geofence = getGeofenceById(geofenceId);
        if (geofence != null) {
            geofence.getVehicles().add(vehicle);
            geofenceRepository.save(geofence);
        }
    }

    // Remove a vehicle from a geofence
    public void removeVehicleFromGeofence(Long geofenceId, Vehicle vehicle) {
        Geofence geofence = getGeofenceById(geofenceId);
        if (geofence != null) {
            geofence.getVehicles().remove(vehicle);
            geofenceRepository.save(geofence);
        }
    }
}
