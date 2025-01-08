package glsib.carpooling.controllers;

import glsib.carpooling.entities.GpsDevice;
import glsib.carpooling.repositories.GpsDeviceRepository;
import glsib.carpooling.repositories.VehicleRepository;
import glsib.carpooling.services.GpsDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/gpsDevices")
public class GpsDeviceController {

    @Autowired
    private GpsDeviceService gpsDeviceService;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private GpsDeviceRepository gpsDeviceRepository;

    @GetMapping
    public String listGpsDevices(Model model) {
        model.addAttribute("gpsDevices", gpsDeviceService.getAllGpsDevices());
        return "gpsDevices_list";
    }
    @GetMapping("/{id}")
    public String GpsDevices(@PathVariable Long id, Model model) {
        GpsDevice gps =gpsDeviceRepository.findById(id).orElse(null);
        model.addAttribute("gpsDevice",gps );
        return "gps_details";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("gpsDevice", new GpsDevice());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "add_gpsDevices";
    }

    @PostMapping("/add")
    public String addGpsDevice(@ModelAttribute GpsDevice gpsDevice, @RequestParam Long vehicleId) {
        gpsDeviceService.addGpsDevice(gpsDevice, vehicleId);
        return "redirect:/admin/gpsDevices";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        GpsDevice gpsDevice = gpsDeviceService.getAllGpsDevices()
                .stream()
                .filter(device -> device.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("GPS Device not found"));
        model.addAttribute("gpsDevice", gpsDevice);
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "gpsDevices_edit";
    }

    @PostMapping("/edit/{id}")
    public String editGpsDevice(@PathVariable Long id, @ModelAttribute GpsDevice gpsDevice, @RequestParam Long vehicleId) {
        gpsDeviceService.updateGpsDevice(id, gpsDevice, vehicleId);
        return "redirect:/admin/gpsDevices";
    }

    @PostMapping("/delete/{id}")
    public String deleteGpsDevice(@PathVariable Long id) {
        gpsDeviceService.deleteGpsDevice(id);
        return "redirect:/admin/gpsDevices";
    }
}
