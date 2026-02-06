package com.example.securtyBasic;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String Greet(){
        return "Helllo";
    }
    @GetMapping("/admin/hello")
    public String GreetAdmin(){
        return "HellloAdmin";
    }
    @GetMapping("/user/hello")
    public String GreetUser(){
        return "HellloUSer";
    }
}
