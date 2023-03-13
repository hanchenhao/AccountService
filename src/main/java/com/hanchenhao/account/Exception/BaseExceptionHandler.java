package com.hanchenhao.account.Exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(InvalidParamException.class)
    ResponseEntity<?> InvalidParamExceptionHandle(InvalidParamException e) {
        val error = ErrorContent.builder()
                .errorMessage(e.getMessage())
                .serviceCode(e.getServiceCode())
                .statusCode(e.getStatusCode())
                .errorType(e.getErrorType())
                .build();
        return ResponseEntity.status(e.getStatusCode() != 0 ? e.getStatusCode()
                        : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<?> AuthenticationExceptionHandle(AuthenticationException e) {
        val error = ErrorContent.builder()
                .errorMessage(e.getMessage())
                .serviceCode(e.getServiceCode())
                .statusCode(e.getStatusCode())
                .errorType(e.getErrorType())
                .build();
        return ResponseEntity.status(e.getStatusCode() != 0 ? e.getStatusCode()
                        : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

}
