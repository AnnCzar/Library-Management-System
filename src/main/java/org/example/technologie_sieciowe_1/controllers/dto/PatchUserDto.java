package org.example.technologie_sieciowe_1.controllers.dto;

public class PatchUserDto {
    private String fullusername;


    private String email;

    public PatchUserDto(String fullusername,String email) {
        this.fullusername = fullusername;
        this.email = email;
    }

    public String getFullname() {
        return fullusername;
    }

    public void setFullname(String fullname) {
        this.fullusername = fullname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
