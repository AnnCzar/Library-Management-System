package org.example.technologie_sieciowe_1.security;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.List;

public class JWTTokenFilter extends OncePerRequestFilter {
    private final String key;

    public JWTTokenFilter(String key) {
        this.key = key;
    }

    @Override   //filtr
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(headerAuth!=null && headerAuth.startsWith("Bearer ")){   //czy heater załączony i ma odpowiednią postać
            String token = headerAuth.split(" ")[1];
            try {
                Claims claims = Jwts.parser()       //parsowanie tokena
                        .setSigningKey(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                // Odczytujemy podstawowe informacje z tokena - identyfikator użytkownika (userId) i rolę
                String userId = ((Integer) claims.get("id")).toString();
                String role = (String) claims.get("role");

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userId, null, List.of(new SimpleGrantedAuthority(role))
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                SecurityContextHolder.clearContext();  //czysczenie kontekstu uwierzytelnienia
            }
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request,response); //zawsze na końcu bo inaczej zapytanie nie zadziała
    }
}