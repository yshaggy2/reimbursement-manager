package com.revature.service;

import com.revature.DAO.AuthDAO;
import com.revature.model.DTO.UserDTO;
import com.revature.model.DTO.OutgoingUserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.model.User;
import com.revature.controller.AuthController;
@Service
public class AuthService {
    private AuthDAO aDAO;
    @Autowired
    public AuthService(AuthDAO aDAO) {
        this.aDAO = aDAO;
    }

    public OutgoingUserDTO login(UserDTO uDTO, HttpSession session) {
        User u = aDAO.findByUsernameAndPassword(uDTO.getUsername(), uDTO.getPassword());

        if (u == null) {
            throw new IllegalArgumentException();
        } else {
            session.setAttribute("userId", u.getUserId());
            session.setAttribute("username", u.getUsername());
            session.setAttribute("role", u.getRole());


            return new OutgoingUserDTO(u.getUserId(), u.getUsername(), u.getRole());
        }
    }
}