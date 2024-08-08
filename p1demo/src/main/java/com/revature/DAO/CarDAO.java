package com.revature.DAO;

import com.revature.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer> {
    public List<Car> findByUserUserId(int userId);
}
