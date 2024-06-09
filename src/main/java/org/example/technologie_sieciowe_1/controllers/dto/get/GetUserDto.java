package org.example.technologie_sieciowe_1.controllers.dto.get;

import io.swagger.v3.oas.annotations.media.Schema;
public class GetUserDto {
    @Schema(description = "User ID", example = "1")
    private Integer id;
    @Schema(description = "User's username", example = "john_doe")
    private String username;
    @Schema(description = "User's email", example = "john@example.com")

    private String email;
    @Schema(description = "User's full name", example = "John Doe")
    private String fullusername;
    public GetUserDto(Integer id, String username, String email, String fullusername) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullusername = fullusername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
