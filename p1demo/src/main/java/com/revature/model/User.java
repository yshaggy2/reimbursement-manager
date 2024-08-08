package com.revature.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity //This makes a class a DB table in your Database (DB Entity)
@Table(name = "users")  //This lets us set attributes like the name of the table
public class User {
    @Id //this makes the field the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This makes our PK auto-increment
    private int userId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    // @Column isn't necessary UNLESS you want to set a name or constraints
    //- nullable: not null constraint
    //- unique: unique constraint
    //- columnDefinition: lets you define more complex constraints (like checks)
    @Column(nullable = false, unique = true, columnDefinition = "TEXT CHECK (LENGTH(username) > 5)")
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition ="text default 'user'" )
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
/*  One to Many relationship: (goes hand in hand with the Many to One in the Car Class)
        mappedBy: THis refers to the field in the car class that maps to this field
            - we are indicating fk in Car class, (field named user)
        fetch: Eager loads the reference on app start
        cascade: This is how we specify what operations cascade down to dependent records
            - cascadeType.ALL: ALL operations cascade down to dependent records (updates/deletes etc.)
        */



    //boilerplate code ------------ no args, all args, getter/setter, toString
    public User() {
    }

    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", cars=" + cars +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
