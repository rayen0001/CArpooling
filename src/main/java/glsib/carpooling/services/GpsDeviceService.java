package glsib.carpooling.services;

import glsib.carpooling.entities.GpsDevice;
import glsib.carpooling.entities.Vehicle;
import glsib.carpooling.repositories.GpsDeviceRepository;
import glsib.carpooling.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpsDeviceService {

    @Autowired
    private GpsDeviceRepository gpsDeviceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<GpsDevice> getAllGpsDevices() {
        return gpsDeviceRepository.findAll();
    }

    public GpsDevice addGpsDevice(GpsDevice gpsDevice, Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        gpsDevice.setVehicle(vehicle);
        return gpsDeviceRepository.save(gpsDevice);
    }

    public GpsDevice updateGpsDevice(Long id, GpsDevice updatedGpsDevice, Long vehicleId) {
        GpsDevice existingDevice = gpsDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GPS Device not found"));

        // Update fields
        existingDevice.setImei(updatedGpsDevice.getImei());
        existingDevice.setModel(updatedGpsDevice.getModel());
        existingDevice.setManufacturer(updatedGpsDevice.getManufacturer());
        existingDevice.setInstallationDate(updatedGpsDevice.getInstallationDate());
        existingDevice.setStatus(updatedGpsDevice.getStatus());

        // Update associated vehicle
        if (vehicleId != null) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            existingDevice.setVehicle(vehicle);
        }

        return gpsDeviceRepository.save(existingDevice);
    }

    public void deleteGpsDevice(Long id) {
        gpsDeviceRepository.deleteById(id);
    }
    public  Long getGpsDeviceId(String imei) {
        GpsDevice gps = gpsDeviceRepository.findByImei(imei).orElse(null);
        return gps == null ? 0 : gps.getId();
    }
}
