package org.example.technologie_sieciowe_1.controllers.dto.respone;

import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class CreateUserResponseDto {
    private Integer id;
    private String userName;

    private String email;
    private String fullUserName;

    public CreateUserResponseDto(Integer id, String userName,  String email, String fullUserName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullUserName = fullUserName;

    }

}
