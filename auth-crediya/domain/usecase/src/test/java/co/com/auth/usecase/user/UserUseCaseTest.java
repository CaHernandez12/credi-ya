package co.com.auth.usecase.user;

import co.com.auth.model.exception.BusinessException;
import co.com.auth.model.exception.BusinessExceptionMessage;
import co.com.auth.model.gateway.BusinessConfigurationGateway;
import co.com.auth.model.role.Role;
import co.com.auth.model.role.gateways.RoleGateway;
import co.com.auth.model.user.User;
import co.com.auth.model.user.gateways.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserUseCaseTest {

    private UserGateway userGateway;
    private BusinessConfigurationGateway businessConfiguration;
    private RoleGateway roleGateway;
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(UserGateway.class);
        businessConfiguration = Mockito.mock(BusinessConfigurationGateway.class);
        roleGateway = Mockito.mock(RoleGateway.class);
        userUseCase = new UserUseCase(userGateway, businessConfiguration, roleGateway);
    }


    private User buildUser() {
        return new User.Builder()
                .email("test@mail.com")
                .documentNumber("12345")
                .roleId(1L)
                .baseSalary(BigDecimal.valueOf(500))
                .build();
    }


    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        User user = buildUser();

        when(userGateway.findByEmailOrDocumentNumber(user.getEmail(), user.getDocumentNumber()))
                .thenReturn(Mono.just(user));

        StepVerifier.create(userUseCase.save(user))
                .expectErrorSatisfies(ex -> {
                    assert ex instanceof BusinessException;
                    assert ((BusinessException) ex).getBusinessExceptionMessage() == BusinessExceptionMessage.USER_ALREADY_EXISTS;
                })
                .verify();

        verify(userGateway).findByEmailOrDocumentNumber(user.getEmail(), user.getDocumentNumber());
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {
        User user = buildUser();

        when(userGateway.findByEmailOrDocumentNumber(user.getEmail(), user.getDocumentNumber()))
                .thenReturn(Mono.empty());
        when(roleGateway.findById(user.getRoleId()))
                .thenReturn(Mono.empty());

        StepVerifier.create(userUseCase.save(user))
                .expectErrorSatisfies(ex -> {
                    assert ex instanceof BusinessException;
                    assert ((BusinessException) ex).getBusinessExceptionMessage() == BusinessExceptionMessage.ROLE_NOT_FOUND;
                })
                .verify();
    }


    @Test
    void shouldReturnFalseWhenDocumentDoesNotExist() {
        when(userGateway.findByDocument("99999"))
                .thenReturn(Mono.empty());

        StepVerifier.create(userUseCase.findByDocument("99999"))
                .expectNext(false)
                .verifyComplete();
    }
}
