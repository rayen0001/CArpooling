package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Geofence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @ElementCollection
    List<GpsLocation> coordinates;
    String type;
    String status;

    @ManyToMany
    @JoinTable(
            name = "geofence_vehicle",
            joinColumns = @JoinColumn(name = "geofence_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    List<Vehicle> vehicles;
}
