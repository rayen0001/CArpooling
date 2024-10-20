package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Table(name = "ride_requests")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user; // The user requesting the ride

    @Column(nullable = false)
    String origin; // Starting point of the requested ride

    @Column(nullable = false)
    String destination; // Destination of the requested ride

    @Column(nullable = false)
    Date requestedTime; // When the ride request was made

    @Column(nullable = false)
    Integer seatsRequested; // Number of seats requested
}
