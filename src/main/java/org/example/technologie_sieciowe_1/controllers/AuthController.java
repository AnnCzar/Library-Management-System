package org.example.technologie_sieciowe_1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.technologie_sieciowe_1.controllers.dto.LoginDto;
import org.example.technologie_sieciowe_1.controllers.dto.LoginResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterDto;
import org.example.technologie_sieciowe_1.controllers.dto.RegisterResponseDto;
import org.example.technologie_sieciowe_1.service.auth_user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints for user authentication and registration")

public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Logged in"),
            @ApiResponse(responseCode = "404", description = "User already exists", content = @Content)
    })
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<RegisterResponseDto> register(@Validated @RequestBody RegisterDto registerDto){
        RegisterResponseDto dto = authService.register(registerDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    @Operation(summary = "Login")
    @SecurityRequirements
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Logged in"),
            @ApiResponse(responseCode = "404", description = "Wrong username", content = @Content),
            @ApiResponse(responseCode = "409", description = "Wrong password", content = @Content)
    })
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        LoginResponseDto dto = authService.login(loginDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete")
    @Operation(summary = "Delete user")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Removed"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
       authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
