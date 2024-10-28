package glsib.carpooling.services;

import glsib.carpooling.entities.GpsDevice;
import glsib.carpooling.repositories.GpsDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpsDeviceService {

    @Autowired
    private GpsDeviceRepository gpsDeviceRepository;

    // Retrieve all GPS devices
    public List<GpsDevice> getAllGpsDevices() {
        return gpsDeviceRepository.findAll();
    }

    // Retrieve a GPS device by ID
    public GpsDevice getGpsDeviceById(Long id) {
        return gpsDeviceRepository.findById(id).orElse(null);
    }

    // Create or update a GPS device
    public GpsDevice saveGpsDevice(GpsDevice gpsDevice) {
        return gpsDeviceRepository.save(gpsDevice);
    }

    // Delete a GPS device by ID
    public void deleteGpsDevice(Long id) {
        gpsDeviceRepository.deleteById(id);
    }

    // Retrieve GPS devices by vehicle ID
    public List<GpsDevice> getGpsDevicesByVehicleId(Long vehicleId) {
        return gpsDeviceRepository.findByVehicleId(vehicleId);
    }
}
