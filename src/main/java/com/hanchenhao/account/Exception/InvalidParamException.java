package com.hanchenhao.account.Exception;
import org.springframework.http.HttpStatus;

public class InvalidParamException extends ServiceException {
    public InvalidParamException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorType(ErrorType.CLIENT);
        this.setServiceCode(10000);
        this.setErrorMessage(message);
    }
}
