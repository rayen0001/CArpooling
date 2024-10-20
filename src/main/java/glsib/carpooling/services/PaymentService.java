package glsib.carpooling.services;

import glsib.carpooling.entities.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment getPayment(Long id);
    List<Payment> getAllPayments();
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
}
