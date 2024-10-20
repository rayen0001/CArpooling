package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "rides")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user; // The user who created the ride

    @Column(nullable = false)
    String origin; // Starting location

    @Column(nullable = false)
    String destination; // Ending location

    @Column(nullable = false)
    Date departureTime; // When the ride departs
    @Column(nullable = false)
    private String status; // Status of the booking (e.g., "Active", "Cancelled", etc.)


    @Column(nullable = false)
    int availableSeats; // Number of available seats
    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RideHistory> rideHistories = new ArrayList<>();
    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks = new ArrayList<>();
    // Add any other fields you need, e.g., price, description, etc.
}
