package co.com.auth.usecase.user;

import co.com.auth.model.exception.BusinessException;
import co.com.auth.model.exception.BusinessExceptionMessage;
import co.com.auth.model.gateway.BusinessConfigurationGateway;
import co.com.auth.model.user.User;
import co.com.auth.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase implements UserUseCaseService {

    private final UserGateway userGateway;
    private final BusinessConfigurationGateway businessConfiguration;

    @Override
    public Mono<User> save(User user) {
        return userGateway.findByEmail(user.getEmail())
                .flatMap(existing -> Mono.<User>error(new BusinessException(BusinessExceptionMessage.USER_ALREADY_EXISTS)))
                .switchIfEmpty(validateSalary(user)
                        .then(userGateway.save(user)));
    }

    @Override
    public Mono<User> getUserById(String id) {
        return null;
    }

    @Override
    public Flux<User> getAllUsers() {
        return null;
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return null;
    }

    private Mono<Void> validateSalary(User user) {
        if (user.getBaseSalary().compareTo(businessConfiguration.getMinBaseSalary()) < 0) {
            return Mono.error(new BusinessException(BusinessExceptionMessage.SALARY_NOT_VALID));
        }
        return Mono.empty();
    }
}
