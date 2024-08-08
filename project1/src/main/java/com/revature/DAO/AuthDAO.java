package com.revature.DAO;

import com.revature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDAO extends JpaRepository<User, Integer> {
    public User findByUsernameAndPassword(String username, String password);
}
