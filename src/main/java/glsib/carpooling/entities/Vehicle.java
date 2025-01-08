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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String type;
    String color;
    String registrationNumber;
    String manufacturer;
    String model;
    Integer year;
    String status;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    List<GpsDevice> gpsDevices;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "vehicles")
    List<Driver> drivers;




}
