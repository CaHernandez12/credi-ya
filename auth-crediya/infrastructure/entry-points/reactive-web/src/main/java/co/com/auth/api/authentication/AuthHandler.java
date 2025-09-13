package co.com.auth.api.authentication;

import co.com.auth.api.authentication.security.JwtUtil;
import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.model.role.gateways.RoleGateway;
import co.com.auth.usecase.user.UserUseCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class AuthHandler {

    private final UserUseCaseService userUseCaseService;
    private final PasswordEncoder passwordEncoder;
    private final RoleGateway roleGateway;
    private final JwtUtil jwtUtil;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(Map.class)
                .flatMap(body -> {
                    String email = (String) body.get("email");
                    String password = (String) body.get("password");

                    return userUseCaseService.findByEmail(email)
                            .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionMessage.USER_NOT_FOUND)))
                            .flatMap(user -> {
                                if (!passwordEncoder.matches(password, user.getPassword())) {
                                    return Mono.error(new TechnicalException(TechnicalExceptionMessage.VALIDATION_LOGIN_FAILED));
                                }
                                return roleGateway.findById(user.getRoleId())
                                        .map(role -> jwtUtil.generateToken(user.getUserId(), role.getName()));
                            });
                })
                .flatMap(token -> ServerResponse.ok().bodyValue(Collections.singletonMap("token", token)))
                .onErrorResume(TechnicalException.class,
                        ex -> ServerResponse.status(HttpStatus.UNAUTHORIZED)
                                .bodyValue(Collections.singletonMap("error", ex.getTechnicalExceptionMessage().getMessage()))
                );
    }



}
