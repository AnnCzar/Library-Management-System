package org.example.technologie_sieciowe_1.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginResponseDto {
    @Schema(description = "JWT Token")
    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
