package glsib.carpooling.services;

import glsib.carpooling.entities.Driver;
import glsib.carpooling.entities.Task;
import glsib.carpooling.entities.Vehicle;
import glsib.carpooling.repositories.DriverRepository;
import glsib.carpooling.repositories.TaskRepository;
import glsib.carpooling.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, VehicleRepository vehicleRepository, TaskRepository taskRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.taskRepository = taskRepository;
    }

    // Retrieve all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Retrieve a driver by ID
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    // Save or update a driver
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // Delete a driver by ID
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    // Assign vehicles to a driver
    public void assignVehiclesToDriver(Long driverId, List<Long> vehicleIds) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            List<Vehicle> vehicles = vehicleRepository.findAllById(vehicleIds);
            driver.setVehicles(vehicles);
            driverRepository.save(driver);
        }
    }

    // Unassign a vehicle from a driver
    public void unassignVehicleFromDriver(Long driverId, Long vehicleId) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.getVehicles().removeIf(vehicle -> vehicle.getId().equals(vehicleId));
            driverRepository.save(driver);
        }
    }

    // Assign a task to a driver
    public void assignTaskToDriver(Long driverId, Task task) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            task.setAssignedTo(driver);
            taskRepository.save(task);
        }
    }

    // Retrieve tasks assigned to a driver
    public List<Task> getTasksByDriverId(Long driverId) {
        return taskRepository.findByAssignedToId(driverId);
    }
}
