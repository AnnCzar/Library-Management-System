package org.example.technologie_sieciowe_1.service.auth_user;

import jakarta.transaction.Transactional;
import org.example.technologie_sieciowe_1.controllers.dto.LoginDto;
import org.example.technologie_sieciowe_1.controllers.dto.LoginResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.AuthEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.AuthRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.example.technologie_sieciowe_1.service.JwtService;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.IncorrectPasswordException;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserAlreadyExistsException;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public RegisterResponseDto register(RegisterDto registerDto){
        Optional<AuthEntity> existingAuth = authRepository.findByUserName(registerDto.getUsername());

        if (existingAuth.isPresent()) {
            throw UserAlreadyExistsException.create(registerDto.getUsername());

        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        authEntity.setUserName(registerDto.getUsername());
        authEntity.setRole(registerDto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);
        return new RegisterResponseDto( userEntity.getId(), authEntity.getUserName(), authEntity.getRole());

    }


    public LoginResponseDto login(LoginDto logindto){

        AuthEntity authEntity = authRepository.findByUserName(logindto.getUserName()).orElseThrow(() -> UserNotFoundException.create(logindto.getUserName()));

        if (!passwordEncoder.matches(logindto.getPassword(), authEntity.getPassword())) {
            throw IncorrectPasswordException.create();
        }
        var token = jwtService.generateToken(authEntity);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new LoginResponseDto(token);

    }

    public void delete(Integer id) {
        if (!authRepository.existsById(id)){
            throw UserNotFoundException.create(id);
        }
    }
}
