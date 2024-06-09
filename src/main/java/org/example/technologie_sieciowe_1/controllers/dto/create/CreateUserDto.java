package org.example.technologie_sieciowe_1.controllers.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CreateUserDto {
    @Schema(description = "User's username", example = "john_doe")
    private String username;
    @NotNull
    @Schema(description = "User's email", example = "john@example.com", required = true)
    private String email;
    @Schema(description = "User's full name", example = "John Doe")
    private String fullusername;


    public CreateUserDto(String username, String email, String fullusername) {
        this.username = username;
        this.email = email;
        this.fullusername = fullusername;
    }

    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullusername() {
        return fullusername;
    }
    public void setFullusername(String fullusername) {
        this.fullusername = fullusername;
    }

}
