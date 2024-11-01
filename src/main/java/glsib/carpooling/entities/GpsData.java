package glsib.carpooling.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // Embed the GpsLocation field
    @Embedded
    GpsLocation location;

    Double speed;
    String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "gps_device_id")
    GpsDevice gpsDevice;
    public LocalDateTime getTimestamp() {
        return location != null ? location.getTimestamp() : null;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        if (location != null) {
            location.setTimestamp(timestamp);
        }
    }
}
