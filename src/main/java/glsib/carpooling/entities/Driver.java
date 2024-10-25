package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String licenseNumber;
    String phoneNumber;
    LocalDate hireDate;

    @ManyToMany
    @JoinTable(
            name = "driver_vehicle",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    List<Vehicle> vehicles;

    @OneToMany(mappedBy = "assignedTo")
    List<Task> tasks = new ArrayList<>();
}
