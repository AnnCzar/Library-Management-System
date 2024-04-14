package org.example.technologie_sieciowe_1.controllers.dto;

public class PatchUserResponseDto {

    private Integer id;
    private String fullname;

    private  String username;

    private String email;


    public PatchUserResponseDto(Integer id, String fullname, String username, String email) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
