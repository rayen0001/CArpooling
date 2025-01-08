package glsib.carpooling.controllers;

import glsib.carpooling.entities.Vehicle;
import glsib.carpooling.repositories.VehicleRepository;
import glsib.carpooling.services.VehicleService;
import glsib.carpooling.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String viewVehicles(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle-list";
    }

    @GetMapping("/{id}")
    public String viewVehicle(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        model.addAttribute("vehicle", vehicle);
        return "vehicle_details";
    }

    @PostMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/new")
    public String showCreateVehicleForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vehicle", new Vehicle());
        return "add_vehicle";
    }

    @PostMapping("")
    public String saveVehicle(@ModelAttribute Vehicle vehicle, @RequestParam Long userId, Model model) {
        try {
            vehicleService.assignVehicleToUser(vehicle, userId);
            model.addAttribute("successMessage", "Vehicle added successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding vehicle: " + e.getMessage());
        }
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vehicle", vehicle);
        return "edit_vehicle";
    }

    @PostMapping("/edit/{id}")
    public String updateVehicle(@ModelAttribute Vehicle updatedVehicle, @RequestParam Long userId, Model model) {
        vehicleService.updateVehicle(updatedVehicle, userId);
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/search")
    public String searchVehicles(@RequestParam String registrationNumber, Model model) {
        List<Vehicle> vehicles = vehicleRepository.findByRegistrationNumber(registrationNumber);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vehicles", vehicles);
        return "vehicle-list";
    }
}
