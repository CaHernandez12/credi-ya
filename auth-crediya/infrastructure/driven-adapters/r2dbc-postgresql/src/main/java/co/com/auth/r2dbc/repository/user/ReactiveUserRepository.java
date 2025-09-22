package co.com.auth.r2dbc.repository.user;

import co.com.auth.model.user.User;
import co.com.auth.r2dbc.adapter.user.data.UserData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ReactiveUserRepository extends ReactiveCrudRepository<UserData, Long> {

    Mono<UserData> findByEmailOrDocumentNumber(String email, String documentNumber);

    Mono<UserData> findByDocumentNumber(String documentNumber);

    Mono<UserData> findByEmail(String email);

    Flux<User> findByEmailIn(List<String> emails);
}
