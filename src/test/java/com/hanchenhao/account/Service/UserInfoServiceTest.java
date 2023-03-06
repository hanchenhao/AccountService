package com.hanchenhao.account.Service;

import com.hanchenhao.account.Converter.Common.UserInfoCommonConverter;
import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {
    private UserInfoServiceImpl userInfoService;
    @Mock
    private UserInfoDAO userInfoDAO;

    @BeforeEach
    public void setup() {
        userInfoService = new UserInfoServiceImpl(userInfoDAO, new UserInfoCommonConverter());
    }

    @Test
    void testGetUserInfoById() {
        String name = "hanchenhao";
        String password = "123456";
        long userId = 1;
        LocalTime time = LocalTime.now();

        var userInfo = com.hanchenhao.account.Model.Persistence.UserInfo.builder()
                .userName(name)
                .password(password)
                .id(userId)
                .createdAt(time)
                .updatedAt(time)
                .build();

        Mockito.doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);

        // Act
        val result =
                userInfoService.getUserInfoById(userId);

        // Assert
        Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("userName", name)
                .hasFieldOrPropertyWithValue("password", password);
        Mockito.verify(userInfoDAO).getUserInfoById(userId);

    }

}
