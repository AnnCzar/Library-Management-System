package org.example.technologie_sieciowe_1.service.auth_user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

    public static ResponseStatusException create (String username){
        UserNotFoundException exception = new UserNotFoundException((String.format("User not found with username: %s", username)));
        return  new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    public static ResponseStatusException create (Integer id){
        UserNotFoundException exception = new UserNotFoundException((String.format("User not found with id: %s", id)));
        return  new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
