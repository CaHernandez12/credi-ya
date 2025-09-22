package co.com.auth.usecase.user;

import co.com.auth.model.auth.Login;
import co.com.auth.model.user.User;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface UserUseCaseService {

    Mono<User> save(User user);

    Mono<Boolean> findByDocument(String document);

    Mono<User> findById(Long userId);

    Mono<String> login (Login login);

    Mono<Map<String, User>> getDataUser(List<String> emails);

}