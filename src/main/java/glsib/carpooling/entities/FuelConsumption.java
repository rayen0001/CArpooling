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
public class FuelConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long vehicleId;
    LocalDateTime timestamp;
    Double fuelVolume;
    Double distanceTravelled;
    Double fuelCost;
    @ManyToOne
    @JoinColumn(name = "id_vehicle")
    Vehicle vehicle;
}
