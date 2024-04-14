package org.example.technologie_sieciowe_1.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.technologie_sieciowe_1.commonTypes.UserRole;

public class RegisterDto {
    @NotBlank(message = "Password cannot be blank")
    @Schema(name = "password", example = "password")
    private String password;
    @NotBlank(message = "Username cannot be blank")
    @Schema(name = "username", example = "username")
    private String username;
    @Schema(name = "role", example = "ROLE_LIBRARIAN")
    private UserRole role;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(name = "email", example = "email@email.com")
    private String email;

    public RegisterDto(String password, String username, UserRole role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
