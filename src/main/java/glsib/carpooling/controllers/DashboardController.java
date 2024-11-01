package glsib.carpooling.controllers;

import glsib.carpooling.entities.Vehicle;
import glsib.carpooling.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll(); // Fetch vehicles as per user context
        model.addAttribute("vehicles", vehicles);
        return "dashboard";
    }
}
