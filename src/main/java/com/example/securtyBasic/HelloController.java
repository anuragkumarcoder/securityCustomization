package com.example.securtyBasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtutils jwtUtils; // Matches your lowercase class name 'jwtutils'

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String Greet() {
        return "Helllo";
    }

    @GetMapping("/admin/hello")
    public String GreetAdmin() {
        return "HellloAdmin";
    }

    @GetMapping("/user/hello")
    public String GreetUser() {
        return "HellloUSer";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            // Step 1: Authenticate the user [06:07:05]
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            // Step 2: Handle failed authentication [06:06:01]
            return "Fail";
        }

        // Step 3: Set the authentication in the Security Context [06:10:11]
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Step 4: Get UserDetails from the authentication object [06:11:43]
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Step 5: Generate and return the JWT Token string [06:12:46, 06:24:04]
        return jwtUtils.generateTokensFromUsername(userDetails.getUsername());
    }
}