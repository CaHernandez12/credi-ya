package co.com.auth.api.authentication;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationManager(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        try{
            String email = jwtUtil.extractUsername(token);
            String rol = jwtUtil.extractRol(token);
            return Mono.just(new UsernamePasswordAuthenticationToken(email, null,
                    Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()))
            ));
        } catch (Exception e){
            return Mono.empty();
        }
    }
}
