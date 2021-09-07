package com.sap.banking.positivepay.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends BusinessException {

    public UnauthorizedAccessException() {
        super("Unauthorized access");
    }

    public UnauthorizedAccessException(String message){
        super(message);
    }

}
