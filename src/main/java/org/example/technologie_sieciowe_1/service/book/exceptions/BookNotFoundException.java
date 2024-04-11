package org.example.technologie_sieciowe_1.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends  RuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }
    public static ResponseStatusException create (String title){
        BookNotFoundException exception = new BookNotFoundException(String.format("Book not found with title %s", title));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
    public static ResponseStatusException create (){
        BookNotFoundException exception = new BookNotFoundException("Book not found");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
