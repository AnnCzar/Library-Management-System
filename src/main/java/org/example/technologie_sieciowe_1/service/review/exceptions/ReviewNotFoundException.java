package org.example.technologie_sieciowe_1.service.review.exceptions;

import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotFoundException extends RuntimeException{

    private ReviewNotFoundException(String message){
        super(message);
    }

    public static ResponseStatusException create (Integer id){
        ReviewNotFoundException exception = new ReviewNotFoundException((String.format("Review not found with id: %s", id)));
        return  new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
