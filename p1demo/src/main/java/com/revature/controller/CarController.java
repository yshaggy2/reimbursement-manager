package com.revature.controller;

import com.revature.DAO.UserDAO;
import com.revature.model.Car;
import com.revature.model.DTO.IncomingCarDTO;
import com.revature.model.User;
import com.revature.service.CarService;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    private CarService cs;
    private UserDAO uDAO;
    //autowire a CarService and a userDAO
    @Autowired
    public CarController(CarService cs, UserDAO uDAO){
        this.cs = cs;
        this.uDAO = uDAO;
    }
    //TODO: a method that takes in the Car and sends it to the CarService
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok().body(cs.getAllCars());

    }
    @PostMapping
    //ResponseEntity<?> so we can send any ResponseEntity back (String or Car in this case)
    public ResponseEntity<?> addCar(@RequestBody IncomingCarDTO newCar) {
        Car c = new Car(0, newCar.getMake(), newCar.getModel(), newCar.isStickShift(), null);

        Optional<User> u = uDAO.findById(newCar.getUser_id());
        if (u.isPresent()) {
            c.setUser(u.get());
            Car returnedCar = cs.addCar(c);
            return ResponseEntity.accepted().body(returnedCar);
        } else {
            return ResponseEntity.status(400).body("User "+newCar.getUser_id()+" not found!");

        }
        /*
            Optionals are used to prevent NullPointerExceptions. The data may or may not exist.
            In other words, the presence of our data is OPTIONAL.
        */


    }
    //Delete
    @DeleteMapping("/{carId}")
    public ResponseEntity<Object> deleteCarById(@PathVariable int carId) {
        cs.deleteCarById(carId);
        return ResponseEntity.ok("Car with ID: "+ carId + " was deleted.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserID(@PathVariable int userId) {
        return ResponseEntity.ok(cs.getCarsByUserId(userId));
    }
}
