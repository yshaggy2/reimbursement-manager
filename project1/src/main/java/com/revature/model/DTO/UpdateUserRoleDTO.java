package com.revature.model.DTO;

public class UpdateUserRoleDTO {
    private Integer userId;
    private String role;

    public UpdateUserRoleDTO() {
    }

    public UpdateUserRoleDTO(Integer userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}