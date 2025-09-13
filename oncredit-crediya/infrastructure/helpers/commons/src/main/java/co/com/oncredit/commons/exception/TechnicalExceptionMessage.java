package co.com.oncredit.commons.exception;

import lombok.Getter;

@Getter
public enum TechnicalExceptionMessage {
    USER_NOT_FOUND("TE-001", "Usuario no encontrado"),
    USER_ALREADY_EXISTS("TE-002", "El usuario ya existe"),
    INVALID_EMAIL("TE-003", "El correo electrónico no es válido"),
    PASSWORD_TOO_WEAK("TE-004", "La contraseña no cumple con los requisitos"),
    DATABASE_ERROR("TE-005", "Error en la base de datos"),
    UNAUTHORIZED_ACCESS("TE-006", "Acceso no autorizado"),
    CONFIGURATION_NOT_FOUND("TE-007", "El configuracion de negocio no existe"),
    VALIDATION_FAILED("TE-008", "validacion fallida");

    private final String code;
    private final String message;

    TechnicalExceptionMessage(String code, String message) {
        this.message = message;
        this.code = code;
    }

}
