package org.example.technologie_sieciowe_1.service.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientBooksAmount extends RuntimeException{
    private InsufficientBooksAmount (String message) {
        super(message);
    }
    public static ResponseStatusException create(){
        InsufficientBooksAmount exception = new InsufficientBooksAmount("There are no available copies of the book.");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

