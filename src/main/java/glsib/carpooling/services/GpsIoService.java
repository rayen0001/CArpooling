package glsib.carpooling.services;

import glsib.carpooling.entities.GpsIo;
import glsib.carpooling.repositories.GpsIoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpsIoService {

    @Autowired
    private GpsIoRepository gpsIoRepository;

    // Retrieve all GPS I/O records
    public List<GpsIo> getAllGpsIoRecords() {
        return gpsIoRepository.findAll();
    }

    // Retrieve a GPS I/O record by ID
    public GpsIo getGpsIoById(Long id) {
        return gpsIoRepository.findById(id).orElse(null);
    }

    // Create or update a GPS I/O record
    public GpsIo saveGpsIo(GpsIo gpsIo) {
        return gpsIoRepository.save(gpsIo);
    }

    // Delete a GPS I/O record by ID
    public void deleteGpsIo(Long id) {
        gpsIoRepository.deleteById(id);
    }

    // Additional methods can be added as needed
    // For example: find records by status or command
    public List<GpsIo> getGpsIoByStatus(String status) {
        return gpsIoRepository.findByStatus(status);
    }

}
