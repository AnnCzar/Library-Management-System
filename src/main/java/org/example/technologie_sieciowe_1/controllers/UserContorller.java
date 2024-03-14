package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserContorller {
    private final UserRepository userRepository;


    @Autowired
    public UserContorller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody UserEntity addUser(@RequestBody UserEntity userEntity){
        return userRepository.save(userEntity);
    }
    @GetMapping("/getAll")
    public @ResponseBody Iterable<UserEntity> getAllUser(){
        return userRepository.findAll();
    }
    @GetMapping("/getUserById")
    public @ResponseBody Optional<UserEntity> getUserById(Integer id){
        return userRepository.findById(id);
    }
    
}
