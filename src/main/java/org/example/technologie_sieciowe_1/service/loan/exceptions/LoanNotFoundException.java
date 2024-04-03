package org.example.technologie_sieciowe_1.service.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotFoundException extends RuntimeException{
    private LoanNotFoundException (String message) {
        super(message);
    }
    public static ResponseStatusException create(Integer id){
        LoanNotFoundException exception = new LoanNotFoundException(String.format("Loan not found with id: %s", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
