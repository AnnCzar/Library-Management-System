package org.example.technologie_sieciowe_1.service.bookDetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDetailsNotFoundException extends RuntimeException{
    private BookDetailsNotFoundException(String message) {
        super(message);
    }
    public static ResponseStatusException create (Integer id) {
        BookDetailsNotFoundException exception = new BookDetailsNotFoundException(String.format("Book deatils not found with id: %s", id));
        return  new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
