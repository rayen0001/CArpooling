package glsib.carpooling.controllers;

import glsib.carpooling.entities.UserActionLog;
import glsib.carpooling.entities.User;
import glsib.carpooling.entities.Vehicle;

import glsib.carpooling.repositories.UserActionLogRepository;
import glsib.carpooling.repositories.UserRepository;
import glsib.carpooling.repositories.VehicleRepository;
import glsib.carpooling.services.UserService;
import glsib.carpooling.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserActionLogRepository userActionLogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService; // Inject VehicleService



    @GetMapping("")
    public String getDashboard(Model model) {
        long totalUsers = userService.findAll().size(); // Fetch total users
        long totalVehicles = vehicleService.getAllVehicles().size(); // Fetch total vehicles


        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalVehicles", totalVehicles);

        return "admindash";
    }



    @GetMapping("/users")
    public String viewUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }


    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user-details";
    }


    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }


    @GetMapping("/vehicles")
    public String viewVehicles(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle-list";
    }


    @GetMapping("/vehicles/{id}")
    public String viewVehicle(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        model.addAttribute("vehicle", vehicle);
        return "vehicle_details";
    }


    @DeleteMapping("/vehicles/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return "redirect:/admin/vehicles";
    }


    @GetMapping("/vehicles/new")
    public String showCreateVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "add_vehicle";
    }


    @PostMapping("/vehicles")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "redirect:/admin/vehicles"; // Redirect to vehicles list
    }


    @GetMapping("/vehicles/edit/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        model.addAttribute("vehicle", vehicle);
        return "edit_vehicle";
    }


    @PostMapping("/vehicles/edit")
    public String updateVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "redirect:/admin/vehicles";
    }


    @GetMapping("/vehicles/search")
    public String searchVehicles(@RequestParam String registrationNumber, Model model) {
        List<Vehicle> vehicles = vehicleRepository.findByRegistrationNumber(registrationNumber);
        model.addAttribute("vehicles", vehicles);
        return "vehicle-list";
    }


    @GetMapping("/logs")
    public String viewUserActionLogs(Model model) {
        List<UserActionLog> logs = userActionLogRepository.findAll();
        model.addAttribute("logs", logs);
        return "logs"; // Thymeleaf template for logs
    }
}
