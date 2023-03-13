package com.hanchenhao.account.Exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends ServiceException{
    public AuthenticationException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
        this.setErrorType(ErrorType.SERVICE);
        this.setServiceCode(20000);
        this.setErrorMessage(message);
    }
}
