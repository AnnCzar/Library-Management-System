//package org.example.technologie_sieciowe_1.service;
//
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.example.technologie_sieciowe_1.LoginForm;
//import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Date;
//
//@Service
//public class LoginService {
//
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//
//    @Value("${jwt.token.key}")
//    private String key;
//
//    // konstruktor z Autowired
//    @Autowired
//    public LoginService(PasswordEncoder passwordEncoder, UserRepository userRepository){
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }
//    public  String userLogin(LoginForm loginForm){
//        //tutaj pobrać dane użytkownika z bazy do porównania
////
////        if(passwordEncoder.matches(loginForm.getPassword()
////                ,"hash hasła z bazy danych"))
//        if(passwordEncoder.matches(loginForm.getPassword()
//                ,"")){
//            long timeMillis = System.currentTimeMillis();
//            String token = Jwts.builder()
//                    .issuedAt(new Date(timeMillis))
//                    .expiration(new Date(timeMillis+5*60*1000))
//                    .claim("id", "id użytkownika z bazy danych")
//                    .claim("role", "rola użytkownika z bazy danych")
//                    .signWith(SignatureAlgorithm.HS256, key)
//                    .compact();
//            return token;
//        } else {
//            return null;    //lub wyjątek
//        }
//    }
//
//}
//// https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
//// informacje o sesji użytkownika przy każdym zapytaniu token przy komunikacji z bazą danych
//// dane o użytkowniku w token, w token nie ma żadnych danych wrażliwych - id, rola, bo są one zakodowane
//// za pomocą klucza symetrycznego ten sam klucz do kodowania i odczytania