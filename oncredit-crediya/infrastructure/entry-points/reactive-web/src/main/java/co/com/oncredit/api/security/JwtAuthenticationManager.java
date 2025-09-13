package co.com.oncredit.api.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        try {
            Long userId = jwtUtil.extractUserId(token);
            String rol = jwtUtil.extractRol(token);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userId, // principal
                    null,  // credentials
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()))
            );
            return Mono.just(auth);
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
