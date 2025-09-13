package co.com.oncredit.api.credit;

import co.com.oncredit.api.credit.dto.CreateCreditDTO;
import co.com.oncredit.api.mapper.CreditDTOMapper;
import co.com.oncredit.commons.exception.TechnicalException;
import co.com.oncredit.commons.exception.TechnicalExceptionMessage;
import co.com.oncredit.usecase.credit.CreditUseCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreditHandler {

    private final CreditDTOMapper creditMapper;
    private final CreditUseCaseService creditUseCaseService;
    private final CreditRequestValidator creditRequestValidator;

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.principal()
                .cast(Authentication.class)
                .flatMap(auth -> {
                    Long userId = Long.parseLong(auth.getName());
                    return serverRequest.bodyToMono(CreateCreditDTO.class)
                            .doOnNext(dto -> log.info("Incoming request body: {}", dto))
                            .flatMap(dto -> creditRequestValidator.isCreditRequestValid(dto)
                                    .map(valid -> dto))
                            .map(creditMapper::toModel)
                            .flatMap(credit -> creditUseCaseService.save(credit, userId))
                            .doOnNext(credit -> log.info("Response Body: {}", credit))
                            .flatMap(credit -> ServerResponse.ok().bodyValue(credit));
                })
                .doOnError(error -> log.error("Error processing request: {}", error.getMessage(), error));
    }

    private Mono<CreateCreditDTO> validateDTO(CreateCreditDTO dto) {
        return Mono.defer(() -> {
            var errors = Stream.of(

                            dto.getAmount() == null  ? "el monto no puede estar vacio" : null,
                            dto.getTerm() == null ? "el plazo no puede estar vacio" : null,
                            //dto.getEmail() == null || dto.getEmail().isBlank() ? "email no puede estar vacío" :
                            //        (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") ? "email inválido" : null),
                            dto.getLoanTypeId() == null ? "el tipo de prestamo no puede estar vacio" : null
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
