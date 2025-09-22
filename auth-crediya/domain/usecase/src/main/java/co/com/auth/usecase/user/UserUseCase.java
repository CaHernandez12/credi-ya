package co.com.auth.usecase.user;

import co.com.auth.model.auth.Login;
import co.com.auth.model.auth.gateways.LoginGateway;
import co.com.auth.model.exception.BusinessException;
import co.com.auth.model.exception.BusinessExceptionMessage;
import co.com.auth.model.gateway.BusinessConfigurationGateway;
import co.com.auth.model.role.gateways.RoleGateway;
import co.com.auth.model.user.User;
import co.com.auth.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserUseCase implements UserUseCaseService {

    private final UserGateway userGateway;
    private final LoginGateway loginGateway;
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
    public Mono<String> login(Login login) {
        return userGateway.findByEmail(login.getEmail())
                .switchIfEmpty(Mono.error(new BusinessException(BusinessExceptionMessage.INVALID_CREDENTIALS)))
                .flatMap(user -> loginGateway.findPassword(login.getPassword(), user.getPassword())
                        .flatMap(valid -> {
                            if (!valid) {
                                return Mono.error(new BusinessException(BusinessExceptionMessage.INVALID_CREDENTIALS));
                            }
                            return roleGateway.findById(user.getRoleId())
                                    .switchIfEmpty(Mono.error(new BusinessException(BusinessExceptionMessage.ROLE_NOT_FOUND)))
                                    .map(role -> loginGateway.generateToken(user.getUserId(), role.getName()));
                        })
                );
    }

    @Override
    public Mono<Map<String, User>> getDataUser(List<String> emails) {
        return userGateway.findByEmails(emails);
    }

    @Override
    public Mono<Boolean> findByDocument(String document) {
        return userGateway.findByDocument(document)
                .hasElement();
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
