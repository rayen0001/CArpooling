package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @ElementCollection
    List<GpsLocation> waypoints;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;
}
