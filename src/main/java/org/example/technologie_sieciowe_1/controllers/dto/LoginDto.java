package org.example.technologie_sieciowe_1.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginDto {
    @Schema(description = "Username", example = "username")
    private String userName;
    @Schema(description = "Password", example = "password")
    private String password;

    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
