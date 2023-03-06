package com.hanchenhao.account.Exception;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


class BaseExceptionHandlerTest {

    @Test
    void invalidParamExceptionHandle() {
        BaseExceptionHandler handler = new BaseExceptionHandler();
        InvalidParamException e = new InvalidParamException("error");
        val error = ErrorContent.builder()
                .errorMessage(e.getMessage())
                .serviceCode(e.getServiceCode())
                .statusCode(e.getStatusCode())
                .errorType(e.getErrorType())
                .build();

        var responseEntity = handler.InvalidParamExceptionHandle(e);
        Assertions.assertThat(responseEntity)
                .isNotNull()
                .hasFieldOrPropertyWithValue("status", e.getStatusCode())
                .hasFieldOrPropertyWithValue("body", error);

    }

    @Test
    void invalidParamExceptionHandleWithNullCode() {
        BaseExceptionHandler handler = new BaseExceptionHandler();
        InvalidParamException e = new InvalidParamException("error");
        e.setStatusCode(0);
        val error = ErrorContent.builder()
                .errorMessage(e.getMessage())
                .serviceCode(e.getServiceCode())
                .statusCode(e.getStatusCode())
                .errorType(e.getErrorType())
                .build();

        var responseEntity = handler.InvalidParamExceptionHandle(e);
        Assertions.assertThat(responseEntity)
                .isNotNull()
                .hasFieldOrPropertyWithValue("status", INTERNAL_SERVER_ERROR.value())
                .hasFieldOrPropertyWithValue("body", error);

    }
}