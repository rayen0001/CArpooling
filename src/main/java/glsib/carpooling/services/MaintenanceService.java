package glsib.carpooling.services;

import glsib.carpooling.entities.Maintenance;
import glsib.carpooling.repositories.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    // Retrieve all maintenance records
    public List<Maintenance> getAllMaintenanceRecords() {
        return maintenanceRepository.findAll();
    }

    // Retrieve a maintenance record by ID
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id).orElse(null);
    }

    // Create or update a maintenance record
    public Maintenance saveMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    // Delete a maintenance record by ID
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }

    // Retrieve maintenance records by vehicle ID
    public List<Maintenance> getMaintenanceByVehicleId(Long vehicleId) {
        return maintenanceRepository.findByVehicleId(vehicleId);
    }
}
