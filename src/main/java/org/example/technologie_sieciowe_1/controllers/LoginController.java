package org.example.technologie_sieciowe_1.controllers;



import org.example.technologie_sieciowe_1.LoginForm;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateUserDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.service.LoginService;
import org.example.technologie_sieciowe_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {
    private final LoginService loginService;
    private  final UserService userService;

    @Autowired
    public LoginController(LoginService loginService, UserService userService){
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
        String token = loginService.userLogin(loginForm);

        if(token==null){
//            return new ResponseEntity<>("Wrong login or password", HttpStatus.UNAUTHORIZED);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password or login");
        } else {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }
    @PostMapping("/process_register")
    public String processRegister(CreateUserDto user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.add(user);


        return "register_success";
    }
}