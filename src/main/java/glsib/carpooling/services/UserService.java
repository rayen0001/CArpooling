package glsib.carpooling.services;

import glsib.carpooling.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll(); // Retrieve all users

    User findById(Long id); // Find a user by ID

    void deactivateUser(Long id); // Deactivate a user

    User save(User user); // Save a new user

    void deleteById(Long id); // Delete a user by ID

    // Add other methods as needed (e.g., find by email)
}
