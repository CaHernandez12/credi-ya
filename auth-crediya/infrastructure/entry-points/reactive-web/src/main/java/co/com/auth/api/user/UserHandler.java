package co.com.auth.api.user;

import co.com.auth.api.mapper.UserDTOMapper;
import co.com.auth.api.user.dto.CreateUserDTO;
import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.usecase.user.UserUseCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserHandler {

    private final UserUseCaseService userUseCaseService;
    private final UserRequestValidator userRequestValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOMapper userDTOMapper;


    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserDTO.class)
                .doOnNext(dto -> log.info("Incoming request body: {}", dto))
                .flatMap(dto -> userRequestValidator.isCreateUserValid(dto)
                        .map(valid -> dto))
                .map(dto -> {
                    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
                    return dto;
                })
                .map(userDTOMapper::toModel)
                .flatMap(userUseCaseService::save)
                .doOnNext(user -> log.info("📤 Response body: {}", user))
                .flatMap(user -> ServerResponse.ok().build())
                .doOnError(error -> log.error("❌ Error processing request", error));
    }

    public Mono<ServerResponse> listenGetListEmails(ServerRequest serverRequest) {
        log.info("Incoming request body: {}", serverRequest);
        return serverRequest.bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .doOnNext(dto -> log.info("Incoming request body: {}", dto))
                .flatMap(userUseCaseService::getDataUser)
                .flatMap(dataMap -> ServerResponse.ok().bodyValue(dataMap))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> listenGETExistingId(ServerRequest serverRequest) {
        log.info("Incoming request body search by id : {}", serverRequest);
        return Mono.justOrEmpty(serverRequest.queryParam("userId"))
                .map(Long::parseLong)
                .flatMap(userId -> userUseCaseService.findById(userId)
                        .map(userDTOMapper::toResponse)
                        .flatMap(dto -> ServerResponse.ok().bodyValue(dto))
                )
                .onErrorResume(TechnicalException.class,
                        e -> ServerResponse.status(500).bodyValue(e.getTechnicalExceptionMessage().name()))
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("Missing required query param: userId"))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

}

