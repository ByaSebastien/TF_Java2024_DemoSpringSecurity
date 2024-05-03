package be.bstorm.tf_java2024_demospringsecurity.api.controllers;

import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello world!!!");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/hello")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello my master!");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/hello/myman")
    public ResponseEntity<String> helloMyMan(
            Authentication authentication
    ){
        User u = (User) authentication.getPrincipal();
        String token = (String) authentication.getCredentials();
        return
                ResponseEntity.ok("Hello " + u.getUsername());
    }
}
