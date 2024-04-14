package org.example.technologie_sieciowe_1.controllers.dto.respone;

import io.swagger.v3.oas.annotations.media.Schema;
public class CreateUserResponseDto {
    @Schema(description = "User ID", example = "1")
    private Integer id;
    @Schema(description = "User's username", example = "john_doe")
    private String userName;
    @Schema(description = "User's email", example = "john@example.com")

    private String email;

    @Schema(description = "User's full name", example = "John Doe")
    private String fullUserName;

    public CreateUserResponseDto(Integer id, String userName,  String email, String fullUserName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullUserName = fullUserName;

    }
}
