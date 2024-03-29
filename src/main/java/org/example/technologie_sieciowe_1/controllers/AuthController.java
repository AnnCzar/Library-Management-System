package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.controllers.dto.LoginDto;
import org.example.technologie_sieciowe_1.controllers.dto.LoginResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterResponseDto;
import org.example.technologie_sieciowe_1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")


public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
//    @PreAuthorize("hasRole('LIBRARIAN')")
    @Secured("ROLE_LIBRARIAN")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto requsetbody){
        RegisterResponseDto dto = authService.register(requsetbody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        LoginResponseDto dto = authService.login(loginDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

}
