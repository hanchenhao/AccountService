package com.hanchenhao.account.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {
    enum ErrorType {
        SERVICE,
        CLIENT,
        UNKNOWN
    }

    private int statusCode; //http状态码
    private int serviceCode; //服务码
    private String errorMessage; //错误信息
    private ServiceException.ErrorType errorType; //错误类型

    public ServiceException(String message) {
        super(message);
    }
}

