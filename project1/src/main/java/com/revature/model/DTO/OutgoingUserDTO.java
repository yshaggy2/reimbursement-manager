package com.revature.model.DTO;
//Here's another common DTO - send a user back to the front end without any
// -sensitive data (password)
// -redundant data (List of Cars)
public class OutgoingUserDTO {
    int userId;
    String username;
    String role;

    public OutgoingUserDTO() {
    }

    public OutgoingUserDTO(int id, String username, String role) {
        this.userId = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}