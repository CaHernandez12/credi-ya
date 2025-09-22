package co.com.auth.api.login;

import co.com.auth.api.login.dto.LoginRequestDTO;
import co.com.auth.api.mapper.LoginDTOMapper;
import co.com.auth.usecase.user.UserUseCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthHandler {

    private final UserUseCaseService userUseCaseService;
    private final LoginDTOMapper loginDTOMapper;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequestDTO.class)
                .doOnNext(dto -> log.info("📥 Received login DTO: {}", dto))
                .map(loginDTOMapper::toModel)
                .flatMap(userUseCaseService::login)
                .map(loginDTOMapper::toResponse)
                .doOnNext(response -> log.info("📤 Mapped to response DTO: {}", response))
                .flatMap(dto -> ServerResponse.ok().bodyValue(dto));
    }
}
