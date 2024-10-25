package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long vehicleId;
    LocalDate maintenanceDate;
    String description;
    String status;
    Double cost;
    @ManyToOne
    @JoinColumn(name = "id_vehicle")
    Vehicle vehicle;
}
