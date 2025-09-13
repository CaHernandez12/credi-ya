package co.com.oncredit.api.credit;

import co.com.oncredit.api.credit.dto.CreateCreditDTO;
import co.com.oncredit.commons.exception.TechnicalException;
import co.com.oncredit.commons.exception.TechnicalExceptionMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class CreditRequestValidator {

    Mono<Boolean> isCreditRequestValid(CreateCreditDTO dto){
        return Mono.defer(() -> {
            var errors = Stream.of(
                    dto.getDocumentNumber() == null || dto.getDocumentNumber().isBlank() ? "el documento no puede estar vacio" : null,
                            dto.getEmail() == null || dto.getEmail().isBlank() ? "el email no puede estar vacio" :
                                    (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") ? "email inválido" : null),
                    dto.getAmount() == null ? "el monto no puede estar vacio" : null,
                    dto.getTerm() == null ? "el plazo no puede estar vacio" : null,
                    dto.getLoanTypeId() == null ? "el tipo de prestam no puede estar vacio" : null
            )
                    .filter(Objects::nonNull)
                    .toList();
            if (!errors.isEmpty()) {
                return Mono.error(new TechnicalException(TechnicalExceptionMessage.VALIDATION_FAILED));
            }
            return Mono.just(true);
        });
    }
}
