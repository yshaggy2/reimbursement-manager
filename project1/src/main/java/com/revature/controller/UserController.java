package com.revature.controller;

import com.revature.model.DTO.UpdateReimbursementStatusDTO;
import com.revature.model.DTO.UpdateUserRoleDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private UserService us;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }
    //Everyone OK
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User u) {
        try {
            User uOut = us.registerUser(u);
            return ResponseEntity.status(201).body(uOut);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    //Manager ONLY
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok(us.getAllUsers());
    }
    //Manager ONLY
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(int userId) {
        try {
            us.deleteUser(userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("User not found.");
        }
        return ResponseEntity.ok("User deleted successfully)");
    }
    //Manager ONLY
    @PatchMapping("/promote/{userId}")
    public ResponseEntity<?> promoteUser(@PathParam("userId") int userId) {
        try {
            return ResponseEntity.ok(us.promoteUser(userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("User not found.");
        }

    }
}
