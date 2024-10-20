package glsib.carpooling.services;

import glsib.carpooling.entities.RideRequest;

import java.util.List;

public interface RideRequestService {
    RideRequest createRideRequest(RideRequest rideRequest);
    RideRequest getRideRequest(Long id);
    List<RideRequest> getAllRideRequests();
    RideRequest updateRideRequest(Long id, RideRequest rideRequest);
    void deleteRideRequest(Long id);
}
