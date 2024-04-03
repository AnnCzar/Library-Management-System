package org.example.technologie_sieciowe_1.service.auth_user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectPasswordException extends RuntimeException{

    private IncorrectPasswordException(String message ){
        super(message);
    }
    public static ResponseStatusException create (){
        IncorrectPasswordException exception = new IncorrectPasswordException("Entered incorrect password");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);

    }

}

