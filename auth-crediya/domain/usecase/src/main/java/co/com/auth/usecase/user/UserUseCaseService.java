package co.com.auth.usecase.user;

import co.com.auth.model.user.User;
import reactor.core.publisher.Mono;

public interface UserUseCaseService {

    Mono<User> save(User user);

    Mono<Boolean> findByDocument(String document);

    Mono<User> findByEmail(String email);
    Mono<User> findById(Long userId);

}