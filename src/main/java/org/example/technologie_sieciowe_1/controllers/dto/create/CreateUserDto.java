package org.example.technologie_sieciowe_1.controllers.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CreateUserDto {
    @Schema(description = "User's username", example = "john_doe")
    private String userName;
    @NotNull
    @Schema(description = "User's email", example = "john@example.com", required = true)
    private String email;
    @Schema(description = "User's full name", example = "John Doe")
    private String fullUserName;


    public CreateUserDto(String userName, String email, String fullUserName) {
        this.userName = userName;
        this.email = email;
        this.fullUserName = fullUserName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullUserName() {
        return fullUserName;
    }
    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

}
