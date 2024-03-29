package org.example.technologie_sieciowe_1.controllers.dto.get;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class GetUserDto {
    private Integer id;
//    private String userName;
//    private String password;
//    private String role;
    private String email;
    private String fullUserName;
    private List<LoanEntity> rental;
    private List<ReviewEntity> review;

    public GetUserDto(Integer id, String email, String fullUserName, List<LoanEntity> rental, List<ReviewEntity> review) {
        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
        this.email = email;
        this.fullUserName = fullUserName;
        this.rental = rental;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

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

    public List<LoanEntity> getRental() {
        return rental;
    }

    public void setRental(List<LoanEntity> rental) {
        this.rental = rental;
    }

    public List<ReviewEntity> getReview() {
        return review;
    }

    public void setReview(List<ReviewEntity> review) {
        this.review = review;
    }
}
