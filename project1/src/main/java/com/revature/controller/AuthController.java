package com.revature.controller;

import com.revature.model.DTO.OutgoingUserDTO;
import com.revature.model.DTO.UserDTO;
import com.revature.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    private AuthService as;

    @Autowired
    public AuthController(AuthService as) {
        this.as = as;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDTO uDTO, HttpSession session) {
        OutgoingUserDTO outUser = as.login(uDTO, session);
        if (outUser == null) {
            return ResponseEntity.badRequest().body("No existing user");
        }
        return ResponseEntity.accepted().body(outUser);
    }
}