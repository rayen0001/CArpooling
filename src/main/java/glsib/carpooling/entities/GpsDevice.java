package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GpsDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String imei;
    String model;
    String manufacturer;
    LocalDate installationDate;
    String status;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    @OneToMany(mappedBy = "gpsDevice", cascade = CascadeType.ALL)
    List<GpsData> gpsData;
}
