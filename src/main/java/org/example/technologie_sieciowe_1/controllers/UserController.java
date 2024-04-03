package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;

import org.example.technologie_sieciowe_1.service.auth_user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Secured("ROLE_LIBRARIAN")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<GetUserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getById")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody GetUserDto getById(Integer id){
        return userService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateUserResponseDto add(@RequestBody CreateUserDto userEntity){

        return userService.add(userEntity);
    }

    @DeleteMapping("/delete")
    @Secured("ROLE_LIBRARIAN")
    public ResponseEntity<Void> delete (Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}



