package co.com.auth.usecase.auth;

import co.com.auth.model.user.User;
import reactor.core.publisher.Mono;

public interface AuthUseCaseService {

    Mono<User> execute(User user);
}
