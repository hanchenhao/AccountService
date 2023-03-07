package com.hanchenhao.account.Converter.Common;

import com.hanchenhao.account.Model.Common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserInfoCommonConverterTest {

    private UserInfo commonUserInfo;
    private com.hanchenhao.account.Model.Persistence.UserInfo persistUserInfo;
    private UserInfoCommonConverter userInfoCommonConverter;

    @BeforeEach
    void setUp() {
        final String name = "hanchenhao";
        final String password = "123456";
        final long userId = 1;
        userInfoCommonConverter = new UserInfoCommonConverter();
        persistUserInfo = com.hanchenhao.account.Model.Persistence.UserInfo.builder()
                .password(password)
                .userName(name)
                .id(userId)
                .build();
        commonUserInfo = UserInfo.builder()
                .userName(name)
                .password(password)
                .id(userId)
                .build();
    }

    @Test
    void doForward() {
        val resultCommon = userInfoCommonConverter.convert(persistUserInfo);
        Assertions.assertEquals(commonUserInfo, resultCommon);
    }

    @Test
    void doBackward() {
        val resultPersist = userInfoCommonConverter.reverse().convert(commonUserInfo);
        Assertions.assertEquals(persistUserInfo, resultPersist);
    }
}