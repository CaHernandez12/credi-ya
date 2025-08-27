package co.com.auth.r2dbc.adapter.user;

import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.model.user.User;
import co.com.auth.model.user.gateways.UserGateway;
import co.com.auth.r2dbc.adapter.user.data.Mapper;
import co.com.auth.r2dbc.adapter.user.data.UserData;
import co.com.auth.r2dbc.repository.user.ReactiveUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserGateway {

    private final ReactiveUserRepository repository;

    @Override
    public Mono<User> save(User user) {
        UserData data = Mapper.toData(user);
        return repository.save(data)
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }

    @Override
    public Mono<User> findByEmail(String email) {

        return repository.findByEmail(email)
                .switchIfEmpty(Mono.empty())
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }
}
