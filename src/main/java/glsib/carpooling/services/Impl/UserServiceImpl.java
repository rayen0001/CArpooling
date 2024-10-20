package glsib.carpooling.services.Impl;

import glsib.carpooling.entities.User;
import glsib.carpooling.repositories.UserRepository;
import glsib.carpooling.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null); // Handle not found case as needed
    }

    @Override
    public void deactivateUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            u.setActive(false); // Assuming you have an `active` field in your User entity
            userRepository.save(u);
        });
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Add other methods as needed (e.g., find by email)
}
