package co.com.oncredit.model.credit.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final BusinessExceptionMessage businessExceptionMessage;

    public BusinessException(BusinessExceptionMessage businessExceptionMessage) {
        super(businessExceptionMessage.getDescription());
        this.businessExceptionMessage = businessExceptionMessage;
    }

    public BusinessException(Throwable cause, BusinessExceptionMessage businessExceptionMessage) {
        super(businessExceptionMessage.getDescription(), cause);
        this.businessExceptionMessage = businessExceptionMessage;
    }
}
