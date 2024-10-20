package glsib.carpooling.controllers;

import glsib.carpooling.entities.User;
import glsib.carpooling.entities.Ride;
import glsib.carpooling.entities.Booking;
import glsib.carpooling.services.UserService;
import glsib.carpooling.services.RideService;
import glsib.carpooling.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<User> users = userService.findAll();
        List<Ride> rides = rideService.findAll();
        List<Booking> bookings = bookingService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("rides", rides);
        model.addAttribute("bookings", bookings);
        return "dashboard"; // Thymeleaf template name
    }

    @PostMapping("/deactivateUser/{id}")
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/cancelRide/{id}")
    public String cancelRide(@PathVariable Long id) {
        rideService.cancelRide(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/cancelBooking/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/dashboard";
    }
}
