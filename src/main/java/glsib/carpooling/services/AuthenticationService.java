package glsib.carpooling.services;

import glsib.carpooling.dtos.LoginUserDto;
import glsib.carpooling.dtos.RegisterUserDto;
import glsib.carpooling.entities.Role;
import glsib.carpooling.entities.User;
import glsib.carpooling.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        // Log the email input
        System.out.println("Attempting to sign up user with email: " + input.getEmail());

        // Check if the user already exists by email
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            System.out.println("Email already exists: " + input.getEmail());
            throw new IllegalArgumentException("Email already exists");
        }

        // Log successful check
        System.out.println("Email is available for registration.");

        // Create a new User object
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setRole(Role.USER); // Ensure Role is an enum or class with USER defined
        user.setPassword(passwordEncoder.encode(input.getPassword())); // Encode the password

        // Log user creation
        System.out.println("Creating user: " + user);

        // Save the user in the repository and return it
        User savedUser = userRepository.save(user);
        System.out.println("User successfully registered: " + savedUser);
        return savedUser;
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
