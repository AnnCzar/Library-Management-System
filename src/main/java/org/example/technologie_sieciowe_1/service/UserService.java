package org.example.technologie_sieciowe_1.service;


import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Iterable<GetUserDto> getAll() {

        var users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(user -> new GetUserDto(user.getId(),
                        user.getEmail(),
                        user.getFullUserName(),
                        user.getRental(),
                        user.getReview()))
                .collect((Collectors.toList()));
    }

    public GetUserDto getById(Integer id){
        var userEntity = userRepository.findById(id).orElse(null);
        return new GetUserDto(userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFullUserName(),
                userEntity.getRental(),
                userEntity.getReview());
    }



    public CreateUserResponseDto add(CreateUserDto user) {

        var userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFullUserName(user.getFullUserName());
        userEntity.setRental(user.getRental());
        userEntity.setReview(user.getReview());

        var newUser = userRepository.save(userEntity);
        return new CreateUserResponseDto(newUser.getId(),
                newUser.getEmail(),
                newUser.getFullUserName(),
                newUser.getRental(),
                newUser.getReview());
    }
    public void delete(Integer id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException();
        }
        userRepository.deleteById(id);
    }

}

