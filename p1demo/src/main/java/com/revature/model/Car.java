package com.revature.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;


    private String make;
    private String model;

    private boolean stickShift;

    /* PK FK relationship (Many to One)

    fetch - defines whether the dependency (User) is eagerly or lazily loaded
        -eager: loads the dependency as soon as the app starts
        -lazy: loads the dependency as soon as it's called for (CAREFUL!)
        @JoinColumn - defnies the column that will be used to join the tables (PK of the User Class)
            -we have to supply the name of the pk that fk is referring to.
            -we use the name of the CLASS field, not the DB column
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public Car() {
    }

    public Car(int carId, String make, String model, boolean stickShift, User user) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.stickShift = stickShift;
        this.user = user;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isStickShift() {
        return stickShift;
    }

    public void setStickShift(boolean stickShift) {
        this.stickShift = stickShift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", stickShift=" + stickShift +
                ", user=" + user +
                '}';
    }
}
