package co.com.auth.api.user;

import co.com.auth.api.user.dto.CreateUserDTO;
//import co.com.auth.api.user.mapper.UserDTOMapper;
import co.com.auth.api.user.util.UserUtil;
import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.commons.logging.LoggingHelper;
import co.com.auth.usecase.user.UserUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserHandler {

    //private final UserDTOMapper userDTOMapper;
    private final UserUseCaseService userUseCaseService;
    private final LoggingHelper loggingHelper;

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        Mono<ServerResponse> mainFlow = serverRequest.bodyToMono(CreateUserDTO.class)
                .flatMap(dto -> validateDTO(dto).thenReturn(dto))
                .flatMap(UserUtil::buildUser)
                .flatMap(userUseCaseService::save)
                .flatMap(user -> {
                    // Aquí imprimes el body de la response
                    loggingHelper.logResponseBody(user);

                    // Construyes la response para enviar al cliente
                    return ServerResponse.ok().bodyValue(user);
                });


        return loggingHelper.logRequestResponse(serverRequest, mainFlow);
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
                            dto.getBaseSalary() == null ? "baseSalary no puede ser nulo" : null
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

