package co.com.auth.usecase.user;

import co.com.auth.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserUseCaseService {

    Mono<User> save(User user);

    Mono<User> getUserById(String id);

    Flux<User> getAllUsers();

    Mono<Void> deleteUser(String id);
}