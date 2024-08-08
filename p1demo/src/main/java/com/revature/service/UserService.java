package com.revature.service;

import com.revature.DAO.UserDAO;
import com.revature.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserDAO userDAO;

    //We can get access to the methods of our UserDao using Dependency injection
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }


    public User registerUser(User newUser) {
        //TODO: error handling and business logic
        //same as User u = userDAO.save(newUser) | return u
        return userDAO.save(newUser);

    }
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
    public User updateUser(String newUsername, int userId) {

        Optional<User> existingUser = userDAO.findById(userId);
        if (existingUser.isPresent()) {
            User u = existingUser.get();
            u.setUsername(newUsername);
            return userDAO.save(u);
        } else {
            return null;
        }
        //Note: the .save() method is used for inserts AND updates
    }
}
