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

    @ManyToMany(mappedBy = "vehicles")
    List<User> users;

    @ManyToMany(mappedBy = "vehicles")
    List<Driver> drivers;

    @ManyToMany
    @JoinTable(
            name = "geofence_vehicle",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "geofence_id"))
    List<Geofence> geofences;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    List<FuelConsumption> fuelConsumptions;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    List<Maintenance> maintenanceRecords;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    List<Event> events;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    List<Route> routes;


    @Embedded
    GpsLocation location;
}
