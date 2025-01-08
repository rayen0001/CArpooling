package glsib.carpooling.services;

import glsib.carpooling.entities.User;
import glsib.carpooling.entities.Vehicle;
import glsib.carpooling.repositories.UserRepository;
import glsib.carpooling.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    // Retrieve all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Retrieve a vehicle by ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    // Create or update a vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Delete a vehicle by ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    // Additional methods can be added as needed
    // For example: find vehicles by status, manufacturer, etc.
    public List<Vehicle> getVehiclesByStatus(String status) {
        return vehicleRepository.findByStatus(status);
    }

    public List<Vehicle> getVehiclesByManufacturer(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber);
    }
    public void assignVehicleToUser(Vehicle vehicle, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        vehicle.setUser(user);
        vehicleRepository.save(vehicle);
    }
    public void updateVehicle(Vehicle updatedVehicle, long userId) {
        Vehicle existingVehicle = vehicleRepository.findById(updatedVehicle.getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Update vehicle details
        existingVehicle.setName(updatedVehicle.getName());
        existingVehicle.setType(updatedVehicle.getType());
        existingVehicle.setColor(updatedVehicle.getColor());
        existingVehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
        existingVehicle.setManufacturer(updatedVehicle.getManufacturer());
        existingVehicle.setModel(updatedVehicle.getModel());
        existingVehicle.setYear(updatedVehicle.getYear());
        existingVehicle.setStatus(updatedVehicle.getStatus());
//        existingVehicle.setUser(updatedVehicle.getUser());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingVehicle.setUser(user);

        vehicleRepository.save(existingVehicle);
    }
}
