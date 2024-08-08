package com.revature.service;

import com.revature.DAO.CarDAO;
import com.revature.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    //autowire a CarDAO

    private CarDAO cDAO;

    @Autowired
    public CarService(CarDAO cDAO) {
        this.cDAO = cDAO;
    }

    public Car addCar(Car newCar) {
        return cDAO.save(newCar);
    }

    public List<Car> getAllCars() {
        return cDAO.findAll();
    }
    public void deleteCarById(int id) {
        //The Car will not fully delete unless it's also deleted from its users' list of cars List<Car>
        Car c = cDAO.findById(id).get();
        c.getUser().getCars().remove(c);
        cDAO.deleteById(id);
    }
    //Find cars from a specific userID int
    public List<Car> getCarsByUserId(int userId) {
        return cDAO.findByUserUserId(userId);
    }
}
