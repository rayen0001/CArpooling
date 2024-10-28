package glsib.carpooling.controllers;

import glsib.carpooling.entities.GpsData;
import glsib.carpooling.services.GpsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gps")
public class GpsController {

    private final GpsDataService gpsDataService;

    @Autowired
    public GpsController(GpsDataService gpsDataService) {
        this.gpsDataService = gpsDataService;
    }

    /**
     * Endpoint to handle the initial handshake with the GPS device.
     * Confirms that the server acknowledges the device and is ready to receive data.
     *
     * @param imei IMEI identifier for the GPS device
     * @return ResponseEntity with a success or error message
     */
    @PostMapping("/handshake")
    public ResponseEntity<String> handleHandshake(@RequestParam String imei) {
        if (imei == null || imei.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid IMEI");
        }

        // Log or perform any specific action needed upon successful handshake
        return ResponseEntity.ok("Handshake successful for device with IMEI: " + imei);
    }

    /**
     * Endpoint to handle GPS data ingestion from the device.
     * Validates and stores the incoming GPS data in the database.
     *
     * @param gpsData GPS data payload from the device
     * @return ResponseEntity with a success or error message
     */
    @PostMapping("/data")
    public ResponseEntity<String> ingestGpsData(@RequestBody GpsData gpsData) {
        if (gpsData == null || gpsData.getImei() == null || gpsData.getTimestamp() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid GPS data");
        }

        // Save the GPS data using the service layer
        gpsDataService.save(gpsData);
        return ResponseEntity.status(HttpStatus.CREATED).body("GPS data recorded successfully");
    }
}
