package org.example.technologie_sieciowe_1.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTTokenFilter extends OncePerRequestFilter {

    private final String key;

    public JWTTokenFilter(String key) {
        this.key = key;
    }


    @Override   // cała logika fitru
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(headerAuth!=null && headerAuth.startsWith(("Bearer"))){
            String token = headerAuth.split(" ")[1];
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseEncryptedClaims(token)
                    .getPayload();
            String userId = (String) claims.get("id"); // to bedzie zapisane w tokenie przy tworzeniu
            String role = (String) claims.get("role");

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, List.of(new SimpleGrantedAuthority(role))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }



        filterChain.doFilter(request,response); // to musi byc zawsze na koncu, bo inaczej zapytanie nie zosatnie wykoanne poprawnie

    }
}
