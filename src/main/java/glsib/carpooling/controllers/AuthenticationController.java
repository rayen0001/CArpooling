package glsib.carpooling.controllers;

import glsib.carpooling.entities.User;
import glsib.carpooling.dtos.LoginUserDto;
import glsib.carpooling.services.AuthenticationService;
import glsib.carpooling.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginUserDto", new LoginUserDto());
        return "login"; // Thymeleaf template name
    }

    @PostMapping("/login")
    public String authenticate(LoginUserDto loginUserDto, Model model, HttpServletResponse response) {
        try {
            // Authenticate user
            User authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getRole());
            System.out.println("Generated JWT Token: " + jwtToken);
            System.out.println("Authenticated user: " + authenticatedUser.getUsername());

            // Create JWT Cookie
            addJwtCookie(response, jwtToken);

            // Determine role-based redirection
            String role = String.valueOf(authenticatedUser.getRole());
            System.out.println("Role: " + role); // Debug statement
            if ("ADMIN".equalsIgnoreCase(role)) {
                return "redirect:/admin"; // Redirect to admin dashboard
            } else {
                return "redirect:/home"; // Redirect to user home
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login"; // Return to login page with error message
        }
    }

    private void addJwtCookie(HttpServletResponse response, String jwtToken) {
        Cookie jwtCookie = new Cookie("JwtToken", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(86400); // 1 day
        response.addCookie(jwtCookie);

        // Log the cookie for debugging
        System.out.println("JWT Cookie Created: " + jwtCookie.getName() + " = " + jwtCookie.getValue());
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Clear the JWT cookie
        Cookie jwtCookie = new Cookie("JwtToken", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // Set cookie max age to 0 to delete it
        response.addCookie(jwtCookie);

        return "redirect:/landing"; // Redirect to login page after logout
    }
}
