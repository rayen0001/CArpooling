package glsib.carpooling.services;

import glsib.carpooling.entities.GpsData;
import glsib.carpooling.entities.GpsDevice;
import glsib.carpooling.entities.GpsLocation;
import glsib.carpooling.repositories.GpsDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Optional;

@Service
public class Codec8Decoder {

    private final GpsDataService gpsDataService;
    private final GpsDeviceRepository gpsDeviceRepository;

    @Autowired
    public Codec8Decoder(GpsDataService gpsDataService, GpsDeviceRepository gpsDeviceRepository) {
        this.gpsDataService = gpsDataService;
        this.gpsDeviceRepository = gpsDeviceRepository;
    }

    public GpsData decodePacket(byte[] asciiPacket) {
        // Convert ASCII packet to binary if needed
        byte[] packet = PacketConverter.convertAsciiToBinary(asciiPacket);

        System.out.println("Converted Binary Data: " + Arrays.toString(packet));

        // Extracting IMEI
        String imei = new String(packet, 0, 15, StandardCharsets.UTF_8).trim();
        System.out.println("Extracted IMEI: " + imei);

        // Check if the device exists
        Optional<GpsDevice> deviceOpt = gpsDeviceRepository.findByImei(imei);
        if (deviceOpt.isEmpty()) {
            throw new IllegalArgumentException("Device not found for IMEI: " + imei);
        }

        // Extracting latitude
        ByteBuffer buffer = ByteBuffer.wrap(packet, 15, 4).order(ByteOrder.BIG_ENDIAN);
        int latitudeBits = buffer.getInt();
        double latitude = latitudeBits / 1e6;
        System.out.println("Raw Latitude Bits: " + latitudeBits);
        System.out.println("Decoded Latitude: " + latitude);

        // Extracting longitude
        buffer = ByteBuffer.wrap(packet, 19, 4).order(ByteOrder.BIG_ENDIAN);
        int longitudeBits = buffer.getInt();
        double longitude = longitudeBits / 1e6;
        System.out.println("Raw Longitude Bits: " + longitudeBits);
        System.out.println("Decoded Longitude: " + longitude);

        // Extracting timestamp
        buffer = ByteBuffer.wrap(packet, 23, 8).order(ByteOrder.BIG_ENDIAN);
        long timestampBits = buffer.getLong();
        long timestampSeconds = timestampBits / 1000;
        LocalDateTime timestamp = Instant.ofEpochSecond(timestampSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("Raw Timestamp Bits: " + timestampBits);
        System.out.println("Decoded Timestamp (seconds): " + timestampSeconds);
        System.out.println("Formatted Timestamp: " + timestamp);

        // Extracting speed
        byte speed = packet[31];
        System.out.println("Raw Speed Bits: " + (speed & 0xFF));
        System.out.println("Decoded Speed: " + (double) speed);

        // Creating GpsLocation and GpsData objects
        GpsLocation location = new GpsLocation();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setTimestamp(timestamp);

        GpsData gpsData = new GpsData();
        gpsData.setImei(imei);
        gpsData.setLocation(location);
        gpsData.setSpeed((double) speed);
        gpsData.setStatus("ACTIVE");
        gpsData.setTimestamp(timestamp);
        gpsData.setGpsDevice(deviceOpt.get());

        // Print final GpsData object for verification
        System.out.println("Decoded GpsData: " + gpsData);

        // Save GPS data
        gpsDataService.saveGpsData(gpsData); // Assuming saveGpsData method exists

        return gpsData;
    }
}
