package co.com.auth.model.user.gateways;

import co.com.auth.model.user.User;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;


public interface UserGateway {

    Mono<User> save(User user);

    Mono<User> findByEmailOrDocumentNumber(String email, String documentNumber);

    Mono<User> findByDocument(String document);

    Mono<User> findByEmail(String email);

    Mono<User> findById(Long userId);

    Mono<Map<String, User>> findByEmails(List<String> emails);

}
