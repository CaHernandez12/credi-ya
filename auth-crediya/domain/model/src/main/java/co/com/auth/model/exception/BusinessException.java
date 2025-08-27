package co.com.auth.model.exception;

import lombok.Getter;

@Getter
public class BusinessException  extends RuntimeException {

    private final BusinessExceptionMessage businessExceptionMessage;

    public BusinessException(BusinessExceptionMessage businessExceptionMessage) {
        this.businessExceptionMessage = businessExceptionMessage;
    }

    public BusinessException(Throwable cause, BusinessExceptionMessage businessExceptionMessage) {
        super(cause);
        this.businessExceptionMessage = businessExceptionMessage;
    }
}
