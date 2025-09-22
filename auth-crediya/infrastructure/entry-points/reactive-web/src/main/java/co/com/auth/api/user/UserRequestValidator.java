package co.com.auth.api.user;

import co.com.auth.api.user.dto.CreateUserDTO;
import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class UserRequestValidator {

    Mono<Boolean> isCreateUserValid(CreateUserDTO dto) {
        return Mono.defer(() -> {
            var errors = Stream.of(
                            dto.getName() == null || dto.getName().isBlank() ? "El nombre no debe estar vacío" : null,
                            dto.getLastName() == null || dto.getLastName().isBlank() ? "El apellido no debe estar vacío" : null,
                            dto.getBirthDate() == null ? "La fecha de nacimiento no debe estar vacía" : null,
                            dto.getAddress() == null || dto.getAddress().isBlank() ? "La dirección no debe estar vacía" : null,
                            dto.getPhone() == null || dto.getPhone().isBlank() ? "El teléfono no debe estar vacío" : null,
                            dto.getDocumentType() == null || dto.getDocumentType().isBlank() ? "El tipo de documento no debe estar vacío" : null,
                            dto.getDocumentNumber() == null || dto.getDocumentNumber().isBlank() ? "El número de documento no debe estar vacío" : null,
                            dto.getEmail() == null || dto.getEmail().isBlank() ? "El email no debe estar vacío" :
                                    (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") ? "Email inválido" : null),
                            dto.getBaseSalary() == null ? "El salario base no debe estar vacío" : null,
                            dto.getRoleId() == null ? "El rol no debe estar vacío" : null
                    )
                    .filter(Objects::nonNull)
                    .toList();

            if (!errors.isEmpty()) {
                // Puedes pasar los errores dentro de la excepción si quieres
                return Mono.error(new TechnicalException(TechnicalExceptionMessage.VALIDATION_FAILED));
            }

            return Mono.just(true);
        });
    }

}
