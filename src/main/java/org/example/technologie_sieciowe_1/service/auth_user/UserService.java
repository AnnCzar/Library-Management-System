package org.example.technologie_sieciowe_1.service.auth_user;


import jakarta.servlet.http.HttpServletRequest;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.AuthRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.example.technologie_sieciowe_1.service.JwtService;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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


    public Iterable<GetUserDto> getAll() {

        var users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(user -> new GetUserDto(user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getFullUserName()))
                .collect(Collectors.toList());
    }

    public GetUserDto getById(Integer id) {
        var user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.create(id));
        return new GetUserDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getFullUserName());
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
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException.create(id);
        }
        userRepository.deleteById(id);
    }

    //    public GetUserDto getInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.getPrincipal() != null) {
//            String username = authentication.getName(); // Nazwa użytkownika
//            var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
//            var user = auth.getUser();
//            return new GetUserDto(auth.getId(),
//                    auth.getUserName(),
//                    user.getEmail(),
//                    user.getFullUserName());
//        } else {
//            // Obsłuż przypadki, gdy uwierzytelnienie lub główny obiekt jest nullem
//            throw UserNotFoundException.create("ania");
//        }
//    }
    public GetUserDto getInfo(String username) {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//    if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.getPrincipal() != null) {
//        String username = authentication.getName(); // Nazwa użytkownika
        var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
        var user = auth.getUser();
        return new GetUserDto(auth.getId(),
                auth.getUserName(),
                user.getEmail(),
                user.getFullUserName());
//    } else {
//        // Obsłuż przypadki, gdy uwierzytelnienie lub główny obiekt jest nullem
//        throw UserNotFoundException.create("ania");
//    }

    }
}


//    public GetUserDto getInfo() {
//
////        try {
////            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
////
////            if (headerAuth == null || !headerAuth.startsWith("Bearer ")) {
////                throw new IllegalArgumentException("Missing or invalid Authorization header");
////            }
////
////            String token = headerAuth.substring(7);
////            JwtService jwtService = null;
////            String username = jwtService.extractUsername(token);
////            var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
////            var user = auth.getUser();
////
////            return new GetUserDto(auth.getId(), auth.getUserName(), user.getEmail(), user.getFullUserName());
////        } catch (Exception e) {
////            throw new RuntimeException("Error getting user info", e);
////        }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
//            String username = authentication.getName(); // Nazwa użytkownika
//            // Możesz dodać więcej szczegółów użytkownika, jeśli są dostępne w Authentication
//            // Na przykład, rolę użytkownika
//            // List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
//            // UserRole role = authorities.stream().findFirst().map(GrantedAuthority::getAuthority).map(UserRole::valueOf).orElse(null);
//
//            // Tutaj możesz zwrócić dowolne informacje o użytkowniku, na przykład nazwę użytkownika
//            var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
//            var user = auth.getUser();
//             return new GetUserDto(auth.getId(),
//                auth.getUserName(),
//                user.getEmail(),
//                user.getFullUserName());
////            return new GetUserDto(username, null, null, null);
//        } else {
//            // Możesz rzucić wyjątek lub zwrócić null w zależności od Twoich wymagań
//            throw  UserNotFoundException.create("dziala");
//        }
//    }


//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName(); // Pobranie nazwy użytkownika
//        var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
//        var user = auth.getUser();
//        return new GetUserDto(auth.getId(),
//                auth.getUserName(),
//                user.getEmail(),
//                user.getFullUserName());
//    }












