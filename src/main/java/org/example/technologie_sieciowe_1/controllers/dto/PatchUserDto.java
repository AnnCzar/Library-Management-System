package org.example.technologie_sieciowe_1.controllers.dto;

public class PatchUserDto {
    private String fullname;


    private String email;

    public PatchUserDto(String fullname,String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
