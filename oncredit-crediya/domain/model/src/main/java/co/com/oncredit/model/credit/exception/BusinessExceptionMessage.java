package co.com.oncredit.model.credit.exception;

public enum BusinessExceptionMessage {

    USER_ALREADY_EXISTS("BE-001", "El usuario ya existe en el sistema"),
    USER_NOT_FOUND("BE-002", "Usuario no encontrado"),
    INVALID_DOCUMENT("BE-003", "El número de documento no es válido"),
    INVALID_EMAIL("BE-004", "El correo electrónico no es válido"),
    SALARY_NOT_VALID("BE-005", "El salario base debe ser mayor que 0 y menor o igual que {}"),
    INVALID_LOAN_TYPE("BE-006", "Tipo de prestamos no encontrado"),
    DOCUMENT_DOES_NOT_MATCH("BE-007", "Rol no encontrado"),
    USER_NOT_VALID("BE-008", "El usuario no existe"),
    ROLE_NOT_FOUND("BE-009", "Rol no encontrado");



    private final String code;
    private final String description;

    BusinessExceptionMessage(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


}
