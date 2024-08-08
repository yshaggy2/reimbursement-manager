package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    //UserController depends on UserService(it needs its methods). so let's constructor inject the service
    private UserService us;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User u = us.registerUser(newUser);

        return ResponseEntity.status(201).body(u);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = us.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        //one liner for now, might polish with error handling
        return ResponseEntity.ok(us.getUserByUsername(username));
    }
    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUsername(@RequestBody String username, @PathVariable int userId) {
        User updatedUser = us.updateUser(username, userId);
        if (updatedUser == null) {
            return ResponseEntity.status(400).body("User not found with ID: " + userId);
        } else {
             return ResponseEntity.ok(updatedUser);
        }
    }
}
