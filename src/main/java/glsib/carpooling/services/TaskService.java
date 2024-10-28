package glsib.carpooling.services;

import glsib.carpooling.entities.Task;
import glsib.carpooling.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Retrieve all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Retrieve a task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Create or update a task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Delete a task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Retrieve tasks by vehicle ID
    public List<Task> getTasksByVehicleId(Long vehicleId) {
        return taskRepository.findByVehicleId(vehicleId);
    }

    // Retrieve tasks assigned to a specific driver
    public List<Task> getTasksByDriverId(Long driverId) {
        return taskRepository.findByAssignedToId(driverId);
    }
}
