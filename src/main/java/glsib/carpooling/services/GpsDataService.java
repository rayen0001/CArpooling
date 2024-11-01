package glsib.carpooling.services;

import glsib.carpooling.entities.GpsData;
import glsib.carpooling.entities.GpsDevice;
import glsib.carpooling.repositories.GpsDataRepository;
import glsib.carpooling.repositories.GpsDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GpsDataService {

    @Autowired
    private GpsDataRepository gpsDataRepository;
    @Autowired
    private GpsDeviceRepository gpsDeviceRepository;

    // Retrieve all GPS data records
    public List<GpsData> getAllGpsData() {
        return gpsDataRepository.findAll();
    }

    // Retrieve a GPS data record by ID
    public GpsData getGpsDataById(Long id) {
        return gpsDataRepository.findById(id).orElse(null);
    }

    // Create or update a GPS data record
    public GpsData saveGpsData(GpsData gpsData) {
        return gpsDataRepository.save(gpsData);
    }

    // Delete a GPS data record by ID
    public void deleteGpsData(Long id) {
        gpsDataRepository.deleteById(id);
    }

    // Retrieve GPS data by GPS device ID
    public List<GpsData> getGpsDataByGpsDeviceId(Long gpsDeviceId) {
        return gpsDataRepository.findByGpsDeviceId(gpsDeviceId);
    }
    public GpsData save(GpsData gpsData) {
        // Find the corresponding GpsDevice using the IMEI
        Optional<GpsDevice> gpsDevice = gpsDeviceRepository.findByImei(gpsData.getImei());
        if (gpsDevice.isPresent()) {
            // Set the GpsDevice in GpsData
            gpsData.setGpsDevice(gpsDevice.get());
        } else {
            throw new IllegalArgumentException("No GPS Device found for the provided IMEI.");
        }

        // Save GPS data to the database
        return gpsDataRepository.save(gpsData);
    }

}
