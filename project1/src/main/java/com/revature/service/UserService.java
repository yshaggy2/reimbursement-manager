package com.revature.service;

import com.revature.DAO.UserDAO;
import com.revature.model.DTO.UpdateUserRoleDTO;
import com.revature.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO uDAO;
    @Autowired
    public UserService(UserDAO userDAO) {
        this.uDAO = userDAO;
    }
    //User OK
    public User registerUser(User u) {
        return uDAO.save(u);
    }
    //Manager ONLY
    public List<User> getAllUsers() {
        return uDAO.findAll();
    }
    public void deleteUser(int userId) {
        if (!uDAO.existsById(userId)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        uDAO.deleteById(userId);

    }
    public User promoteUser(int userId) {
        System.out.println(userId);
        Optional<User> o = uDAO.findById(userId);
        User u;
        if (o.isPresent()) {
            u = o.get();
            u.setRole("manager");
            System.out.println(u);
            return uDAO.save(u);
        } else {
            throw new IllegalArgumentException();
        }
    }
/*
    OPTIONAL: Update an employee’s role to manager

 */
}
