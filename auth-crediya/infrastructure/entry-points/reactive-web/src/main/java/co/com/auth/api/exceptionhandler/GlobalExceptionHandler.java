package co.com.auth.api.exceptionhandler;

import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.model.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", ex.getBusinessExceptionMessage().getCode());
        body.put("message", ex.getBusinessExceptionMessage().getDescription());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body); // 409 Conflict
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Map<String, Object>> handleTechnicalException(TechnicalException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", ex.getTechnicalExceptionMessage().getCode());
        body.put("message", ex.getTechnicalExceptionMessage().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
