package com.revature.model.DTO;

public class ReimbursementDTO {
    private String description;
    private double amount;
    private Integer userId; // Assuming userId is enough to associate a User

    // No-argument constructor
    public ReimbursementDTO() {
    }

    // All-arguments constructor
    public ReimbursementDTO(String description, double amount, Integer userId) {
        this.description = description;
        this.amount = amount;
        this.userId = userId;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
