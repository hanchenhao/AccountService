package com.hanchenhao.account.Exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorContent {
    private int statusCode; //http状态码
    private int serviceCode; //服务码
    private String errorMessage; //错误信息
    private ServiceException.ErrorType errorType; //错误类型
}
