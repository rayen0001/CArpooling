package glsib.carpooling.services.Impl;

import glsib.carpooling.entities.Ride;
import glsib.carpooling.repositories.RideRepository;
import glsib.carpooling.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }
    @Override
    public void cancelRide(Long id) {
        Optional<Ride> booking = rideRepository.findById(id);
        booking.ifPresent(b -> {
            // Assuming you have a field to represent booking status
            b.setStatus("Cancelled"); // Change the status to "Cancelled"
            rideRepository.save(b); // Save the updated booking
        });
    }

    @Override
    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    @Override
    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }

    @Override
    public Ride updateRide(Long id, Ride ride) {
        if (rideRepository.existsById(id)) {
            ride.setId(id); // Set the ID of the ride to update
            return rideRepository.save(ride);
        }
        return null; // Or throw an exception
    }
}
