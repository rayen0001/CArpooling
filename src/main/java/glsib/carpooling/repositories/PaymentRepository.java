package glsib.carpooling.repositories;

import glsib.carpooling.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can add custom query methods here if needed
}
