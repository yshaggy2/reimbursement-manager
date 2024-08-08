package com.revature.model.DTO;

public class IncomingCarDTO {
    private int userID;
    private String make;
    private String model;
    private boolean stickShift;

    public IncomingCarDTO() {
    }

    public IncomingCarDTO(int userID, String make, String model, boolean stickShift) {
        this.userID = userID;
        this.make = make;
        this.model = model;
        this.stickShift = stickShift;
    }

    public int getUser_id() {
        return userID;
    }

    public void setUser_id(int user_id) {
        this.userID = user_id;
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

    @Override
    public String toString() {
        return "IncomingCarDTO{" +
                "user_id=" + userID +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", stickShift=" + stickShift +
                '}';
    }
}
