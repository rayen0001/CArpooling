package glsib.carpooling.controllers;

import glsib.carpooling.entities.GpsData;
import glsib.carpooling.services.GpsDataService;
import glsib.carpooling.services.Codec8Decoder; // Import the Codec8Decoder
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate; // Import SimpMessagingTemplate
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/gps")
public class GpsController {

    private final GpsDataService gpsDataService;
    private final Codec8Decoder codec8Decoder;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GpsController(GpsDataService gpsDataService, Codec8Decoder codec8Decoder, SimpMessagingTemplate messagingTemplate) {
        this.gpsDataService = gpsDataService;
        this.codec8Decoder = codec8Decoder;
        this.messagingTemplate = messagingTemplate;
    }
    @PostMapping("/handshake")
    public ResponseEntity<String> handleHandshake(@RequestParam String imei) {
        if (imei == null || imei.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid IMEI");
        }
        return ResponseEntity.ok("Handshake successful for device with IMEI: " + imei);
    }
    @PostMapping("/data")
    public ResponseEntity<String> ingestGpsData(@RequestBody byte[] rawData) {
        try {
            System.out.println("Raw data from the controller: " + Arrays.toString(rawData));
            GpsData gpsData = codec8Decoder.decodePacket(rawData);
            gpsDataService.save(gpsData);

            try {
                System.out.println("Sending GPS Data: " + gpsData);
                messagingTemplate.convertAndSend("/topic/gps-data", gpsData);
            } catch (Exception e) {
                System.err.println("Error sending GPS data via WebSocket: " + e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("GPS data recorded successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing GPS data");
        }
    }
}
