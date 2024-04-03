package org.example.technologie_sieciowe_1.controllers.dto.create;

import jakarta.validation.constraints.NotNull;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class CreateUserDto {

    private String userName;
    @NotNull
    private String email;
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
