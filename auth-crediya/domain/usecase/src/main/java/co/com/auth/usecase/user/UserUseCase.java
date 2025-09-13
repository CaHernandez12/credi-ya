package co.com.auth.usecase.user;

import co.com.auth.model.exception.BusinessException;
import co.com.auth.model.exception.BusinessExceptionMessage;
import co.com.auth.model.gateway.BusinessConfigurationGateway;
import co.com.auth.model.role.gateways.RoleGateway;
import co.com.auth.model.user.User;
import co.com.auth.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class UserUseCase implements UserUseCaseService {

    private final UserGateway userGateway;
    private final BusinessConfigurationGateway businessConfiguration;
    private final RoleGateway roleGateway;

    @Override
    public Mono<User> save(User user) {
        return userGateway.findByEmailOrDocumentNumber(user.getEmail(), user.getDocumentNumber())
                .flatMap(existing -> Mono.<User>error(new BusinessException(BusinessExceptionMessage.USER_ALREADY_EXISTS)))
                .switchIfEmpty(
                        roleGateway.findById(user.getRoleId())
                                .switchIfEmpty(Mono.error(new BusinessException(BusinessExceptionMessage.ROLE_NOT_FOUND)))
                                .then(validateSalary(user))
                                .then(userGateway.save(user))
                );
    }

    @Override
    public Mono<Boolean> findByDocument(String document) {
        return userGateway.findByDocument(document)
                .hasElement();
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userGateway.findByEmail(email);
    }

    @Override
    public Mono<User> findById(Long userId) {
        return userGateway.findById(userId);
    }

    private Mono<Void> validateSalary(User user) {
        if (user.getBaseSalary().compareTo(businessConfiguration.getMinBaseSalary()) >= 0
                || user.getBaseSalary().compareTo(BigDecimal.ZERO) < 0) {
            return Mono.error(new BusinessException(BusinessExceptionMessage.SALARY_NOT_VALID));
        }
        return Mono.empty();
    }


}
