package org.example.technologie_sieciowe_1.controllers.dto.respone;

import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class CreateUserResponseDto {
    private Integer id;
//    private String userName;
//    private String password;
//    private String role;
    private String email;
    private String fullUserName;
    private List<LoanEntity> rental;
    private List<ReviewEntity> review;

    public CreateUserResponseDto(Integer id, String email, String fullUserName, List<LoanEntity> rental, List<ReviewEntity> review) {
        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
        this.email = email;
        this.fullUserName = fullUserName;
        this.rental = rental;
        this.review = review;
    }

}
