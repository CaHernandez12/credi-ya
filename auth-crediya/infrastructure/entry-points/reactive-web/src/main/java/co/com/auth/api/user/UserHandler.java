package co.com.auth.api.user;

import co.com.auth.api.mapper.UserDTOMapper;
import co.com.auth.api.user.dto.CreateUserDTO;
import co.com.auth.api.user.util.UserUtil;
import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.usecase.user.UserUseCaseService;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserHandler {

    private final UserUseCaseService userUseCaseService;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOMapper userDTOMapper;


    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserDTO.class)
                .doOnNext(dto -> log.info("Incoming request body: {}", dto))
                .flatMap(this::validateDTO)
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

    public Mono<ServerResponse> listenGETExistingId(ServerRequest serverRequest) {
        log.info("Incoming request body search by id : {}", serverRequest);
        return Mono.justOrEmpty(serverRequest.queryParam("userId"))
                .map(Long::parseLong)
                .flatMap(userId -> userUseCaseService.findById(userId)
                        .map(userDTOMapper::toResponse)
                        .flatMap(dto -> ServerResponse.ok().bodyValue(dto))
                )
                .onErrorResume(TechnicalException.class,
                        e-> ServerResponse.status(500).bodyValue(e.getTechnicalExceptionMessage().name()))
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("Missing required query param: userId"))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    private Mono<CreateUserDTO> validateDTO(CreateUserDTO dto) {
        return Mono.defer(() -> {
            var errors = Stream.of(
                            dto.getName() == null || dto.getName().isBlank() ? "el nombre no puede estar vacio" : null,
                            dto.getLastName() == null || dto.getLastName().isBlank() ? "lastName no puede estar vacío" : null,
                            dto.getBirthDate() == null ? "birthDate no puede ser nulo" : null,
                            dto.getDocumentType() == null || dto.getDocumentType().isBlank() ? "documentType no puede estar vacío" : null,
                            dto.getDocumentNumber() == null || dto.getDocumentNumber().isBlank() ? "documentNumber no puede estar vacío" : null,
                            dto.getEmail() == null || dto.getEmail().isBlank() ? "email no puede estar vacío" :
                                    (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") ? "email inválido" : null),
                            dto.getBaseSalary() == null ? "baseSalary no puede ser nulo" : null,
                            dto.getRoleId() == null ? "el rol no puede estar vacio" : null
                    )
                    .filter(Objects::nonNull)
                    .toList();
            if (!errors.isEmpty()) {
                return Mono.error(new TechnicalException(TechnicalExceptionMessage.VALIDATION_FAILED));
            }
            return Mono.just(dto);
        });
    }
}

