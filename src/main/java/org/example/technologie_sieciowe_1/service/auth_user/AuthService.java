package org.example.technologie_sieciowe_1.service.auth_user;

import jakarta.transaction.Transactional;
import org.example.technologie_sieciowe_1.commonTypes.UserRole;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Transactional
    public RegisterResponseDto register(RegisterDto registerDto){
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(registerDto.getusername());

        if (existingAuth.isPresent()) {
            throw UserAlreadyExistsException.create(registerDto.getusername());

        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setusername(registerDto.getusername());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        authEntity.setusername(registerDto.getusername());
        authEntity.setRole(registerDto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);
        return new RegisterResponseDto( userEntity.getId(), authEntity.getusername(), authEntity.getRole());

    }

    public LoginResponseDto login(LoginDto logindto){

        AuthEntity authEntity = authRepository.findByUsername(logindto.getusername()).orElseThrow(() -> UserNotFoundException.create(logindto.getusername()));

        if (!passwordEncoder.matches(logindto.getPassword(), authEntity.getPassword())) {
            throw IncorrectPasswordException.create();
        }
        var token = jwtService.generateToken(authEntity);
        String role = authEntity.getRole().toString();
        System.out.println(role);
        Integer id = authEntity.getUser().getId();
        return new LoginResponseDto(token, role, id);

    }

    @Transactional
    public void delete(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.create(id));

        AuthEntity authEntity = (AuthEntity) authRepository.findByUser(userEntity)
                .orElseThrow(() -> UserNotFoundException.create(id));

        authRepository.delete(authEntity);
        userRepository.delete(userEntity);
    }
    @Transactional
    public RegisterResponseDto updateLoginAndPassword(String username, RegisterDto registerDto) {
        AuthEntity authEntity = authRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.create(username));

        // Aktualizacja username
        if (registerDto.getusername() != null && !registerDto.getusername().isEmpty()) {
            authEntity.setusername(registerDto.getusername());
        }
        else{
            authEntity.setusername(authEntity.getusername());
        }

        // Aktualizacja hasła
        if (registerDto.getPassword() != null && !registerDto.getPassword().isEmpty()) {
            authEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        }else{
            authEntity.setPassword(authEntity.getusername());
        }

        authRepository.save(authEntity);

        return new RegisterResponseDto(authEntity.getUser().getId(), authEntity.getusername(), authEntity.getRole());
    }
}
