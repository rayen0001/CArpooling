package glsib.carpooling.controllers;

import glsib.carpooling.dtos.LoginUserDto;
import glsib.carpooling.entities.Driver;
import glsib.carpooling.entities.Task;
import glsib.carpooling.entities.User;
import glsib.carpooling.services.AuthenticationService;
import glsib.carpooling.services.DriverService;
import glsib.carpooling.services.JwtService;
import glsib.carpooling.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FrontController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final DriverService driverService;
    private final TaskService taskService;


    @Autowired
    public FrontController(JwtService jwtService, AuthenticationService authenticationService, DriverService driverService, TaskService taskService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.driverService = driverService;
        this.taskService = taskService;
    }

    @PostMapping("/auth/in")
    public ResponseEntity<?> authenticatefront(@RequestBody LoginUserDto loginUserDto) {
        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getRole());

            // Return the token and role to the client
            return ResponseEntity.ok(Map.of(
                    "message", "Authentication successful",
                    "token", jwtToken,
                    "role", authenticatedUser.getRole(),
                    "username", authenticatedUser.getUsername()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // Retrieve all drivers
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    // Retrieve a driver by ID
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Driver driver = driverService.getDriverById(id);
        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    // Save or update a driver
    @PostMapping("/drivers")
    public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    // Delete a driver by ID
    @DeleteMapping("drivers/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Assign vehicles to a driver
    @PostMapping("/{driverId}/vehicles")
    public ResponseEntity<Void> assignVehiclesToDriver(@PathVariable Long driverId, @RequestBody List<Long> vehicleIds) {
        driverService.assignVehiclesToDriver(driverId, vehicleIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Unassign a vehicle from a driver
    @DeleteMapping("/{driverId}/vehicles/{vehicleId}")
    public ResponseEntity<Void> unassignVehicleFromDriver(@PathVariable Long driverId, @PathVariable Long vehicleId) {
        driverService.unassignVehicleFromDriver(driverId, vehicleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Assign a task to a driver
    @PostMapping("/{driverId}/tasks")
    public ResponseEntity<Void> assignTaskToDriver(@PathVariable Long driverId, @RequestBody Task task) {
        driverService.assignTaskToDriver(driverId, task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Retrieve tasks assigned to a driver
    @GetMapping("/{driverId}/tasks")
    public ResponseEntity<List<Task>> getTasksByDriverId(@PathVariable Long driverId) {
        List<Task> tasks = driverService.getTasksByDriverId(driverId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    // Retrieve all tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Retrieve a task by ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // Save or update a task
    @PostMapping("/tasks")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // Delete a task by ID
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve tasks by vehicle ID
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Task>> getTasksByVehicleId(@PathVariable Long vehicleId) {
        List<Task> tasks = taskService.getTasksByVehicleId(vehicleId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


}
