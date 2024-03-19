package org.example.technologie_sieciowe_1.controllers.dto.create;

import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class CreateUserDto {

    private String userName;
    private String password;
    private String role;
    private String email;
    private String fullUserName;
    private List<LoanEntity> rental;
    private List<ReviewEntity> review;

    public CreateUserDto(String userName, String password, String role, String email, String fullUserName, List<LoanEntity> rental, List<ReviewEntity> review) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.fullUserName = fullUserName;
        this.rental = rental;
        this.review = review;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
