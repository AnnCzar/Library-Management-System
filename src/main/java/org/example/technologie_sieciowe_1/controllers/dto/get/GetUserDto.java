package org.example.technologie_sieciowe_1.controllers.dto.get;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;
import java.util.stream.Collector;

public class GetUserDto {
    private Integer id;
    private String userName;
    private String email;
    private String fullUserName;
//    private List<LoanEntity> loans;   // add method which get info about user and their loans and reviews
//    private List<ReviewEntity> reviews;

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
