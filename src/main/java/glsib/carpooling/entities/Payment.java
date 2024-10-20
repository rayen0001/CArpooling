package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Table(name = "payments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    Booking booking; // The booking related to this payment

    @Column(nullable = false)
    Double amount; // Payment amount

    @Column(nullable = false)
    String paymentMethod; // Method of payment (e.g., Credit Card, PayPal)

    @Column(nullable = false)
    Date paymentDate; // Date of the payment
}
