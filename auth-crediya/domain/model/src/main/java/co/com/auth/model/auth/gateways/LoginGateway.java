package co.com.auth.model.auth.gateways;

import reactor.core.publisher.Mono;

public interface LoginGateway {

    Mono<Boolean> findPassword(String password, String hashedPassword);

    String generateToken(Long userId, String roleName);
}
