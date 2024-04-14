package org.example.technologie_sieciowe_1.controllers.dto.get;

import io.swagger.v3.oas.annotations.media.Schema;
public class GetUserDto {
    @Schema(description = "User ID", example = "1")
    private Integer id;
    @Schema(description = "User's username", example = "john_doe")
    private String userName;
    @Schema(description = "User's email", example = "john@example.com")

    private String email;
    @Schema(description = "User's full name", example = "John Doe")
    private String fullUserName;
    public GetUserDto(Integer id, String userName, String email, String fullUserName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullUserName = fullUserName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
