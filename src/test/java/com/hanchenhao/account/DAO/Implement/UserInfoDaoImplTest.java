package com.hanchenhao.account.DAO.Implement;

import com.hanchenhao.account.DAO.Mapper.UserInfoMapper;
import com.hanchenhao.account.Model.Persistence.UserInfo;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;


@ExtendWith(MockitoExtension.class)
class UserInfoDaoImplTest {

    @Mock
    private UserInfoMapper mapper;

    @InjectMocks
    UserInfoDaoImpl userInfoDAO;


    @Test
    void getUserInfoById() {
        String name = "hanchenhao";
        long userId = 1;
        String password = "123456";
        LocalTime time = LocalTime.now();
        UserInfo userInfo = UserInfo.builder()
                .userName(name)
                .id(userId)
                .password(password)
                .createdAt(time)
                .updatedAt(time)
                .build();
        Mockito.doReturn(userInfo).when(mapper).getUserInfoById(userId);

        val result = userInfoDAO.getUserInfoById(userId);
        Assertions.assertEquals(userInfo,result);
    }
}