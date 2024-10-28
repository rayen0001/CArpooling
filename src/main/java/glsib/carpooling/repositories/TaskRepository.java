package glsib.carpooling.repositories;

import glsib.carpooling.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByVehicleId(Long vehicleId);
    List<Task> findByAssignedToId(Long driverId);}
