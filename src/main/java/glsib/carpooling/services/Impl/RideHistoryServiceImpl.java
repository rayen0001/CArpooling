package glsib.carpooling.services.Impl;

import glsib.carpooling.entities.RideHistory;
import glsib.carpooling.repositories.RideHistoryRepository;
import glsib.carpooling.services.RideHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideHistoryServiceImpl implements RideHistoryService {

    @Autowired
    private RideHistoryRepository rideHistoryRepository;

    @Override
    public RideHistory createRideHistory(RideHistory rideHistory) {
        return rideHistoryRepository.save(rideHistory);
    }

    @Override
    public RideHistory getRideHistory(Long id) {
        return rideHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<RideHistory> getAllRideHistories() {
        return rideHistoryRepository.findAll();
    }

    @Override
    public RideHistory updateRideHistory(Long id, RideHistory rideHistory) {
        rideHistory.setId(id);
        return rideHistoryRepository.save(rideHistory);
    }

    @Override
    public void deleteRideHistory(Long id) {
        rideHistoryRepository.deleteById(id);
    }
}
