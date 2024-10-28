package glsib.carpooling.services;

import glsib.carpooling.entities.Event;
import glsib.carpooling.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Retrieve all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Retrieve an event by ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    // Create or update an event
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // Delete an event by ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // Retrieve events by vehicle ID
    public List<Event> getEventsByVehicleId(Long vehicleId) {
        return eventRepository.findByVehicleId(vehicleId);
    }
}
