package org.example.technologie_sieciowe_1.service.auth_user;


import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
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
                        user.getUserName(),
                        user.getEmail(),
                        user.getFullUserName()))
                .collect(Collectors.toList());
    }

    public GetUserDto getById(Integer id){
        var userEntity = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.create(id));
        return new GetUserDto(userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getFullUserName());
    }



    public CreateUserResponseDto add(CreateUserDto user) {

        var userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setEmail(user.getEmail());
        userEntity.setFullUserName(user.getFullUserName());

        var newUser = userRepository.save(userEntity);
        return new CreateUserResponseDto(newUser.getId(),
                newUser.getUserName(),
                newUser.getEmail(),
                newUser.getFullUserName());
    }
    public void delete(Integer id) {
        if(!userRepository.existsById(id)){
            throw UserNotFoundException.create(id);
        }
        userRepository.deleteById(id);
    }


}

