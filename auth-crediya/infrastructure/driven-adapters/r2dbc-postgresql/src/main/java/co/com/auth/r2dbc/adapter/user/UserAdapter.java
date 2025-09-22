package co.com.auth.r2dbc.adapter.user;

import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.model.user.User;
import co.com.auth.model.user.gateways.UserGateway;
import co.com.auth.r2dbc.adapter.user.data.Mapper;
import co.com.auth.r2dbc.adapter.user.data.UserData;
import co.com.auth.r2dbc.repository.user.ReactiveUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserGateway {

    private final ReactiveUserRepository repository;
    private final TransactionalOperator operator;

    @Override
    public Mono<User> save(User user) {
        System.out.println(user.toString());
        UserData data = Mapper.toData(user);
        return repository.save(data)
                .as(operator::transactional)
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }

    @Override
    public Mono<User> findByEmailOrDocumentNumber(String email, String documentNumber) {
        return repository.findByEmailOrDocumentNumber(email, documentNumber)
                .switchIfEmpty(Mono.empty())
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }

    @Override
    public Mono<User> findByDocument(String document) {
        return repository.findByDocumentNumber(document)
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }

    @Override
    public Mono<User> findById(Long userId) {
        log.info("Find user by id: {}", userId);
        return repository.findById(userId)
                .doOnNext(user -> log.info("✅ Found user in DB: {}", user))
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("❌ User with id={} not found in DB", userId);
                    return Mono.error(new TechnicalException(TechnicalExceptionMessage.USER_NOT_FOUND));
                }))
                .map(Mapper::toModel);
    }

    @Override
    public Mono<Map<String, User>> findByEmails(List<String> emails) {
        return repository.findByEmailIn(emails)
                .collectList()
                .map(users -> users.stream()
                        .collect(Collectors.toMap(User::getEmail, Function.identity())));
    }
}
