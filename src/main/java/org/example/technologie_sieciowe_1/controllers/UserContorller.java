package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.example.technologie_sieciowe_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserContorller {
    private final UserService userService;


    @Autowired
    public UserContorller(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAll")
    public @ResponseBody Iterable<UserEntity> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getById")
    public @ResponseBody UserEntity getById(Integer id){
        return userService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody UserEntity add(@RequestBody UserEntity userEntity){
        return userService.add(userEntity);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete (Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
