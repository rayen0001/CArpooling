package glsib.carpooling.services;

import glsib.carpooling.entities.Ride;

import java.util.List;

public interface RideService {
    Ride createRide(Ride ride);
    List<Ride> findAll();
    Ride getRideById(Long id);
    void deleteRide(Long id);
    Ride updateRide(Long id, Ride ride);

    void cancelRide(Long id);
}
