package co.com.oncredit.commons.exception;

import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {

    private final TechnicalExceptionMessage technicalExceptionMessage;

    public TechnicalException(TechnicalExceptionMessage technicalExceptionMessage) {
        this.technicalExceptionMessage = technicalExceptionMessage;
    }

    public TechnicalException(Throwable cause, TechnicalExceptionMessage technicalExceptionMessage) {
        super(technicalExceptionMessage.getMessage(), cause);
        this.technicalExceptionMessage = technicalExceptionMessage;
    }
}
