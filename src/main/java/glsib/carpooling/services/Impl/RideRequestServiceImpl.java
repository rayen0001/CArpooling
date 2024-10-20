package glsib.carpooling.services.Impl;

import glsib.carpooling.entities.RideRequest;
import glsib.carpooling.repositories.RideRequestRepository;
import glsib.carpooling.services.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestServiceImpl implements RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest createRideRequest(RideRequest rideRequest) {
        return rideRequestRepository.save(rideRequest);
    }

    @Override
    public RideRequest getRideRequest(Long id) {
        return rideRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<RideRequest> getAllRideRequests() {
        return rideRequestRepository.findAll();
    }

    @Override
    public RideRequest updateRideRequest(Long id, RideRequest rideRequest) {
        rideRequest.setId(id);
        return rideRequestRepository.save(rideRequest);
    }

    @Override
    public void deleteRideRequest(Long id) {
        rideRequestRepository.deleteById(id);
    }
}
