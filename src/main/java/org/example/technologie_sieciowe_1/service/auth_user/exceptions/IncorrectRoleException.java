package org.example.technologie_sieciowe_1.service.auth_user.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;
public class IncorrectRoleException extends  RuntimeException{
    private IncorrectRoleException(String message){
        super(message);
    }

//    public static ResponseStatusException create (){
//        IncorrectRoleException exception = new IncorrectRoleException("Role have to be ROLE_READER or ROLE_LIBRARIAN");
//        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
//    }
    public static ResponseStatusException create() {
        String message = "Role have to be ROLE_READER or ROLE_LIBRARIAN";
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
}
}
