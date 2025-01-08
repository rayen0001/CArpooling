package glsib.carpooling.controllers;

import glsib.carpooling.entities.UserActionLog;
import glsib.carpooling.entities.User;
import glsib.carpooling.entities.Vehicle;

import glsib.carpooling.repositories.UserActionLogRepository;
import glsib.carpooling.repositories.UserRepository;
import glsib.carpooling.repositories.VehicleRepository;
import glsib.carpooling.services.UserService;
import glsib.carpooling.services.VehicleService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/logs")
    public String viewUserActionLogs(Model model) {
        List<UserActionLog> logs = userActionLogRepository.findAll();
        model.addAttribute("logs", logs);
        return "logs"; // Thymeleaf template for logs
    }
}
