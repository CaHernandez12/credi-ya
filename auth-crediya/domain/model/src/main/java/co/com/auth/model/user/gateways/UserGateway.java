package co.com.auth.model.user.gateways;

import co.com.auth.model.user.User;
import reactor.core.publisher.Mono;


public interface UserGateway {

    Mono<User> save(User user);

    Mono<User> findByEmail(String email);

}
