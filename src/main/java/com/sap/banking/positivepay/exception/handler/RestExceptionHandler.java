package com.sap.banking.positivepay.exception.handler;

import com.sap.banking.positivepay.exception.BusinessException;
import com.sap.banking.positivepay.exception.PositivePayNotFoundException;
import com.sap.banking.positivepay.exception.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller advice for RestController with {@link RestController} annotation.
 * This advice will be applied for all such RestControllers
 *
 * @author I313873
 *
 */
@RestControllerAdvice(annotations = { RestController.class })
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle exceptions thrown from all RequestMappings
     *
     * @param sourceException
     * @param request
     * @return
     */
    @ExceptionHandler({ PositivePayNotFoundException.class, UnauthorizedAccessException.class})
    public ResponseEntity<Object> handleRestServiceExceptions(BusinessException sourceException, WebRequest request) {
        if (sourceException instanceof PositivePayNotFoundException) {
            return ResponseEntity.notFound().build();
        }
        try {
            return super.handleException(sourceException, request);
        }catch (Exception ex1) {
            // if no mapping found, return as internal server error
            return new ResponseEntity<Object>(sourceException, INTERNAL_SERVER_ERROR);
        }
    }
}
