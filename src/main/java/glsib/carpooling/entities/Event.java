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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String eventType;
    Long vehicleId;
    String imei;
    LocalDateTime timestamp;
    String description;
    String status;

    @ManyToOne
    @JoinColumn(name = "id_vehicle")
    Vehicle vehicle;
}
