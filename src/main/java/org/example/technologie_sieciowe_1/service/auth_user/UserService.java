package org.example.technologie_sieciowe_1.service.auth_user;


import io.swagger.v3.oas.annotations.Operation;
import org.example.technologie_sieciowe_1.controllers.dto.PatchUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.PatchUserResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.AuthEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.AuthRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.technologie_sieciowe_1.commonTypes.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    @Operation(summary = "Get all users", description = "Retrieve all users")
    public Iterable<GetUserDto> getAll() {

        var users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(user -> new GetUserDto(user.getId(),
                        user.getusername(),
                        user.getEmail(),
                        user.getFullusername()))
                .collect(Collectors.toList());
    }
    @Operation(summary = "Get user by ID", description = "Retrieve user by ID")
    public GetUserDto getById(Integer id) {
        var user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.create(id));
        return new GetUserDto(user.getId(),
                user.getusername(),
                user.getEmail(),
                user.getFullusername());
    }

    @Operation(summary = "Add new user", description = "Create a new user")
    public CreateUserResponseDto add(CreateUserDto user) {

        var userEntity = new UserEntity();
        userEntity.setusername(user.getusername());
        userEntity.setEmail(user.getEmail());
        userEntity.setFullusername(user.getFullusername());

        var newUser = userRepository.save(userEntity);
        return new CreateUserResponseDto(newUser.getId(),
                newUser.getusername(),
                newUser.getEmail(),
                newUser.getFullusername());
    }
    @Operation(summary = "Delete user", description = "Delete user by ID")
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException.create(id);
        }
        userRepository.deleteById(id);
    }
    @Operation(summary = "Get user info", description = "Retrieve user information")
    public GetUserDto getInfo(String username) {

        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        var user = auth.getUser();
        return new GetUserDto(auth.getId(),
                auth.getusername(),
                user.getEmail(),
                user.getFullusername());

    }
    @Operation(summary = "Update user", description = "Update user by ID")
    public PatchUserResponseDto update (Integer id, PatchUserDto patchUserDto){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.create(id));
        AuthEntity auth = (AuthEntity) authRepository.findByUserId(id).orElseThrow(() -> UserNotFoundException.create(id));
        if(patchUserDto.getFullname() == null) {
            user.setFullusername(user.getFullusername());
        } else {
            user.setFullusername(patchUserDto.getFullname());
        }

        if(patchUserDto.getEmail() == null) {
            user.setEmail(user.getEmail());
        } else {
            user.setEmail(patchUserDto.getEmail());
        }

        user.setusername(auth.getusername());

        userRepository.save(user);
        return new PatchUserResponseDto(
                user.getId(),
                user.getusername(),
                user.getFullusername(),
                user.getEmail()
                );
    }
    @Operation(summary = "Update my info", description = "Update authenticated user's information")
    public PatchUserResponseDto updateMyInfo(String username, PatchUserDto patchUserDto) {
        AuthEntity auth = authRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.create(username));

        UserEntity user = userRepository.findById(auth.getId())
                .orElseThrow(() -> UserNotFoundException.create(username));

        if (patchUserDto.getFullname() != null) {
            user.setFullusername(patchUserDto.getFullname());
        }else {
            user.setFullusername(patchUserDto.getFullname());
        }
        if (patchUserDto.getEmail() != null) {
            user.setEmail(patchUserDto.getEmail());
        } else {
            user.setEmail(patchUserDto.getEmail());
        }

        user.setusername(auth.getusername());
        userRepository.save(user);

        return new PatchUserResponseDto(
                user.getId(),
                user.getFullusername(),
                user.getusername(),

                user.getEmail()
        );
    }
    @Transactional(readOnly = true)
    public List<GetUserDto> getAllReaders() {
        List<AuthEntity> authEntities = (List<AuthEntity>) authRepository.findAll();

        return authEntities.stream()
                .filter(authEntity -> authEntity.getRole() == UserRole.ROLE_READER)
                .map(authEntity -> {
                    UserEntity userEntity = authEntity.getUser();
                    return new GetUserDto(
                            userEntity.getId(),
                            authEntity.getusername(),
                            userEntity.getEmail(),
                            userEntity.getFullusername()
                    );
                })
                .collect(Collectors.toList());
    }
    @Operation(summary = "Get current user", description = "Retrieve the currently authenticated user information")
    public GetUserDto getCurrentUser(String username) {
        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        var user = auth.getUser();
        return new GetUserDto(auth.getId(),
                auth.getusername(),
                user.getEmail(),
                user.getFullusername());
    }


}













