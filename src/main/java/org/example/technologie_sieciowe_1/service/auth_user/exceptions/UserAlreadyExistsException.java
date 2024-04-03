package org.example.technologie_sieciowe_1.service.auth_user.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends RuntimeException {
    private UserAlreadyExistsException(String message) {
        super(message);
    }

    public static ResponseStatusException create (String username){
        UserAlreadyExistsException exception = new UserAlreadyExistsException(String.format("The username %s is taken", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }


}
