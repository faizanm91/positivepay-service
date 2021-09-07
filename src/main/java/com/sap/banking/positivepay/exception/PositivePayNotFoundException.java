package com.sap.banking.positivepay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@link ResponseStatus} annotation is handled by
 * {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler} to set the status code if this
 * exception is thrown
 *
 * @author Sachin
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PositivePayNotFoundException extends BusinessException{

    private static final String messageFormat = "PositivePay with id %s not found";

    public PositivePayNotFoundException(String applicationId) {
        super(String.format(messageFormat, applicationId));
    }

    public PositivePayNotFoundException(String applicationId, Throwable cause) {
        super(String.format(messageFormat, applicationId), cause);
    }

    public PositivePayNotFoundException(Throwable cause) {
        super(cause);
    }
}
