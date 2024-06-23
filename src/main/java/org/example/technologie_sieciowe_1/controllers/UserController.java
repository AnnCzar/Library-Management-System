package org.example.technologie_sieciowe_1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.technologie_sieciowe_1.controllers.dto.PatchUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.PatchUserResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;

import org.example.technologie_sieciowe_1.service.auth_user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@PostAuthorize("isAuthenticated()")
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getInfo")
    @Operation(summary = "Get user info", description = "Retrieve user information")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<GetUserDto> getInfo(Principal principal) {
        String username = principal.getName();
        GetUserDto getUserDto = userService.getInfo(username);
        return new ResponseEntity<>(getUserDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all users", description = "Retrieve all users")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "The request succeeded")
    public @ResponseBody Iterable<GetUserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getById")
    @Operation(summary = "Get user by ID", description = "Retrieve user by ID")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public @ResponseBody GetUserDto getById(Integer id){
        return userService.getById(id);
    }

    @PostMapping("/add")
    @Operation(summary = "Add new user", description = "Create a new user")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "User added")
    public @ResponseBody CreateUserResponseDto add(@RequestBody CreateUserDto userEntity){

        return userService.add(userEntity);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete user", description = "Delete user by ID")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    public ResponseEntity<Void> delete (Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateUserInfo/{id}")
    @Operation(summary = "Update user", description = "Update user by ID")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public @ResponseBody ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PatchUserDto patchUserDto){
        userService.update(id, patchUserDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("updateMyInfo")
    @Operation(summary = "Update my info", description = "Update authenticated user's information")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public @ResponseBody PatchUserResponseDto updateMyInfo (@RequestBody PatchUserDto patchUserDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username = authentication.getName();
        return userService.updateMyInfo(username, patchUserDto);

    }
    @GetMapping("/getAllReaders")
    @Operation(summary = "Get all readers", description = "Retrieve all users with role READER")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded")
    })
    public ResponseEntity<List<GetUserDto>> getAllReaders() {
        List<GetUserDto> readers = userService.getAllReaders();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }
    @GetMapping("/getCurrentUser")
    @Operation(summary = "Get current user", description = "Retrieve the currently authenticated user information")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<GetUserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        GetUserDto getUserDto = userService.getInfo(username);
        return new ResponseEntity<>(getUserDto, HttpStatus.OK);
    }
}


