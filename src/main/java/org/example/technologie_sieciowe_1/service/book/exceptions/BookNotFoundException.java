package org.example.technologie_sieciowe_1.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends  RuntimeException{
    private BookNotFoundException (String message){
        super(message);
    }
    public static ResponseStatusException create (){
        BookNotFoundException exception = new BookNotFoundException("Book not found");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
