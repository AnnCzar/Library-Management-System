package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.commonTypes.UserRole;
import org.example.technologie_sieciowe_1.controllers.dto.LoginDto;
import org.example.technologie_sieciowe_1.controllers.dto.LoginResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.AuthEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.AuthRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto register(RegisterDto registerDto){
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
    // ten void zmienic pozniej
    // sprawdzenie czy istanieje taki uzytkownik
    public LoginResponseDto login(LoginDto loginDto){
//        AuthEntity authEntity = authRepository.findByUserName(loginDto.getUserName()).orElseThrow(()-> new HttpClientErrorException.NotFound(""));
        AuthEntity authEntity = authRepository.findByUserName(loginDto.getUserName()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found"));

//        if (!authEntity.getPassword().matches(passwordEncoder.encode(loginDto.getPassword()))){
//            throw new RuntimeException();
//        }
        if (!passwordEncoder.matches(loginDto.getPassword(), authEntity.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDto(token);

    }
}
