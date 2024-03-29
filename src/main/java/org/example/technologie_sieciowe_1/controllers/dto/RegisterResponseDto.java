package org.example.technologie_sieciowe_1.controllers.dto;

import org.example.technologie_sieciowe_1.commonTypes.UserRole;

public class RegisterResponseDto {
    private Integer userId;


    private String username;
    private UserRole role;

    public RegisterResponseDto(Integer userId, String username, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
