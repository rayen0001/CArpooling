package glsib.carpooling.services;

import glsib.carpooling.entities.RideHistory;

import java.util.List;

public interface RideHistoryService {
    RideHistory createRideHistory(RideHistory rideHistory);
    RideHistory getRideHistory(Long id);
    List<RideHistory> getAllRideHistories();
    RideHistory updateRideHistory(Long id, RideHistory rideHistory);
    void deleteRideHistory(Long id);
}
