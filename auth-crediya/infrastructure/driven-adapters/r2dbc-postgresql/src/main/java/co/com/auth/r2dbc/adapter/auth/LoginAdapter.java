package co.com.auth.r2dbc.adapter.auth;

import co.com.auth.commons.security.JwtUtil;
import co.com.auth.model.auth.gateways.LoginGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginAdapter implements LoginGateway {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Boolean> findPassword(String password, String hashedPassword) {
        return Mono.just(passwordEncoder.matches(password, hashedPassword));
    }

    @Override
    public String generateToken(Long userId, String roleName) {
        return jwtUtil.generateToken(userId, roleName);
    }
}
