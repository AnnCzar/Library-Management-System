//package org.example.technologie_sieciowe_1.service;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.example.technologie_sieciowe_1.infrastructure.LoginForm;
//import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class LoginService {
//
//    private UserRepository userRepository;
//    private final  PasswordEncoder passwordEncoder;
//
//    @Value("${jwt.token.key}")
//    private String key;
//    @Autowired
//    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//
//
//    public String userLogin(LoginForm loginForm){
//        // tutaj pobrac dane uzytkonwia z bazy danych
//        if(passwordEncoder.matches(loginForm.getPassword(), "hash hasła z bazy danych")){
//
//            long timeMillis = System.currentTimeMillis();
//            String token = Jwts.builder()
//                    .issuedAt(new Date(timeMillis))
//                    .expiration(new Date(timeMillis+5*60*1000))
//                    .claim("id", "id użytkownika  z baz ydnaych")
//                    .claim("role", "ro.a uzytkownika z bayz danyc")
//                    .signWith(SignatureAlgorithm.HS256, key)
//                    .compact();
//            return token;
//
//            //
//        }else{
//            return null;
//        }
//    }
//}
