package com.hanchenhao.account.Converter.Service;

import com.hanchenhao.account.Model.Common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoServiceConverterTest {

    private UserInfoServiceConverter userInfoServiceConverter;
    private UserInfo commonUserinfo;

    private com.hanchenhao.account.Model.Service.UserInfo serviceUserInfo;

    private final String name = "hanchenhao";
    private final long userId = 1;
    private final String  password = "123456";


    @BeforeEach
    void setup() {
        userInfoServiceConverter = new UserInfoServiceConverter();
        commonUserinfo = UserInfo.builder()
                .password(password)
                .userName(name)
                .id(userId)
                .build();
        serviceUserInfo = com.hanchenhao.account.Model.Service.UserInfo.builder()
                .userName(name)
                .id(userId)
                .build();
    }

    @Test
    void doForward() {
        val userInfo = com.hanchenhao.account.Model.Service.UserInfo.builder()
                .userName(name)
                .id(userId)
                .build();
        val result = userInfoServiceConverter.convert(commonUserinfo);
        Assertions.assertEquals(userInfo, result);
    }

    @Test
    void doBackward() {
        val userInfo = UserInfo.builder()
                .userName(name)
                .id(userId)
                .build();
        val result = userInfoServiceConverter.reverse().convert(serviceUserInfo);
        Assertions.assertEquals(userInfo, result);
    }
}