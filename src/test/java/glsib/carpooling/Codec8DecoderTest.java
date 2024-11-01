package glsib.carpooling;

import glsib.carpooling.entities.GpsData;
import glsib.carpooling.entities.GpsDevice;
import glsib.carpooling.entities.GpsLocation;
import glsib.carpooling.repositories.GpsDeviceRepository;
import glsib.carpooling.services.Codec8Decoder;
import glsib.carpooling.services.GpsDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class Codec8DecoderTest {

    @Mock
    private GpsDataService gpsDataService;

    @Mock
    private GpsDeviceRepository gpsDeviceRepository;

    @InjectMocks
    private Codec8Decoder codec8Decoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDecodePacket() {
        // Mock packet data
        byte[] packet = new byte[]{
                49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 0, // IMEI
                -84, 75, 32, 3,        // Latitude
                -77, -24, 0, 0,        // Longitude
                0, 1, -117, 120, 40, 120, 0, 55, // Timestamp (epoch), Speed
                0, 0, 0, 0, 0, 0, 0, 0  // Padding (if needed)
        };

        // Mock device data
        String imei = "123456789012345";
        GpsDevice mockDevice = new GpsDevice();
        mockDevice.setImei(imei);
        when(gpsDeviceRepository.findByImei(imei)).thenReturn(Optional.of(mockDevice));

        // Decode packet
        GpsData gpsData = codec8Decoder.decodePacket(packet);

        // Assertions
        assertNotNull(gpsData);
        assertEquals(imei, gpsData.getImei());
        assertEquals(mockDevice, gpsData.getGpsDevice());

        // Verify location data
        GpsLocation location = gpsData.getLocation();
        assertNotNull(location);
        assertEquals(0.048225, location.getLatitude(), 1e-6); // Expected latitude value
        assertEquals(1309.008766, location.getLongitude(), 1e-6); // Expected longitude value
        assertEquals(LocalDateTime.of(2021, 4, 14, 12, 0), location.getTimestamp()); // Expected timestamp

        // Verify other GPS data fields
        assertEquals(55.0, gpsData.getSpeed());
        assertEquals("ACTIVE", gpsData.getStatus());
    }
}
