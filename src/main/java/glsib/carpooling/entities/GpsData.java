package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GpsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String imei;
    Double latitude;
    Double longitude;
    Double speed;
    LocalDateTime timestamp;
    String status;

    @ManyToOne
    @JoinColumn(name = "gps_device_id")
    GpsDevice gpsDevice;
}
