package org.example.technologie_sieciowe_1.service;

import org.apache.catalina.User;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.ReviewRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
//import org.example.technologie_sieciowe_1.security.PasswordConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.token.key}")
    private String key;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Iterable<GetUserDto> getAll() {

        var users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(user -> new GetUserDto(user.getId(),
                        user.getUserName(),
                        user.getPassword(),
                        user.getRole(),
                        user.getEmail(),
                        user.getFullUserName(),
                        user.getRental(),
                        user.getReview()))
                .collect((Collectors.toList()));
    }

    public GetUserDto getById(Integer id){
        var userEntity = userRepository.findById(id).orElse(null);
        return new GetUserDto(userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getEmail(),
                userEntity.getFullUserName(),
                userEntity.getRental(),
                userEntity.getReview());
    }



    public CreateUserResponseDto add(CreateUserDto user) {

        var userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setUserName(user.getUserName());
        userEntity.setFullUserName(user.getFullUserName());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword())); // Kodowanie hasła
        userEntity.setRole(user.getRole());
        userEntity.setRental(user.getRental());
        userEntity.setReview(user.getReview());

        var newUser = userRepository.save(userEntity);
        return new CreateUserResponseDto(newUser.getId(),
                newUser.getUserName(),
                newUser.getPassword(),
                newUser.getRole(),
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

