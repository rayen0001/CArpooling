package glsib.carpooling.services;

import glsib.carpooling.entities.FuelConsumption;
import glsib.carpooling.repositories.FuelConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelConsumptionService {

    @Autowired
    private FuelConsumptionRepository fuelConsumptionRepository;

    // Retrieve all fuel consumption records
    public List<FuelConsumption> getAllFuelConsumptions() {
        return fuelConsumptionRepository.findAll();
    }

    // Retrieve a fuel consumption record by ID
    public FuelConsumption getFuelConsumptionById(Long id) {
        return fuelConsumptionRepository.findById(id).orElse(null);
    }

    // Create or update a fuel consumption record
    public FuelConsumption saveFuelConsumption(FuelConsumption fuelConsumption) {
        return fuelConsumptionRepository.save(fuelConsumption);
    }

    // Delete a fuel consumption record by ID
    public void deleteFuelConsumption(Long id) {
        fuelConsumptionRepository.deleteById(id);
    }

    // Retrieve fuel consumption records by vehicle ID
    public List<FuelConsumption> getFuelConsumptionsByVehicleId(Long vehicleId) {
        return fuelConsumptionRepository.findByVehicleId(vehicleId);
    }
}
