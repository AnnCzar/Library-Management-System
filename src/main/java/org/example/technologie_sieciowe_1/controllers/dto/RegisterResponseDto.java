package org.example.technologie_sieciowe_1.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.technologie_sieciowe_1.commonTypes.UserRole;

public class RegisterResponseDto {
    @Schema(description = "User ID", example = "123")
    private Integer userId;
    @Schema(description = "Username", example = "john_doe")
    private String username;
    @Schema(description = "User role", example = "ROLE_READER")
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
